package ir.logicfan.core.util

import java.io.Serializable
import java.util.*

class JalaliCalendar : Serializable {

    var year: Int = 0
    var month: Int = 0
    var day: Int = 0

    /**
     * @return yesterday date
     */
    val yesterday: JalaliCalendar
        get() = getDateByDiff(-1)

    /**
     * @return tomorrow date
     */
    val tomorrow: JalaliCalendar
        get() = getDateByDiff(1)

    /**
     * @return day Of Week
     */
    val dayOfWeek: Int
        get() = toGregorian().get(Calendar.DAY_OF_WEEK)

    /**
     * @return get first day of week
     */
    val firstDayOfWeek: Int
        get() = toGregorian().firstDayOfWeek

    /**
     * @return day name
     */
    val dayOfWeekString: String
        get() {
            when (dayOfWeek) {
                1 -> return "یک‌شنبه"
                2 -> return "دوشنبه"
                3 -> return "سه‌شنبه"
                4 -> return "چهارشنبه"
                5 -> return "پنجشنبه"
                6 -> return "جمعه"
                7 -> return "شنبه"
                else -> return "نامعلوم"
            }
        }

    /**
     * @return month name
     */
    val monthString: String
        get() {
            when (month) {
                1 -> return "فروردین"
                2 -> return "اردیبهشت"
                3 -> return "خرداد"
                4 -> return "تیر"
                5 -> return "مرداد"
                6 -> return "شهریور"
                7 -> return "مهر"
                8 -> return "آبان"
                9 -> return "آذر"
                10 -> return "دی"
                11 -> return "بهمن"
                12 -> return "اسفند"
                else -> return "نامعلوم"
            }
        }


    /**
     * get String with the following format :
     * یکشنبه ۱۲ آبان
     *
     * @return String format
     */

    val dayOfWeekDayMonthString: String
        get() = "$dayOfWeekString $day $monthString"

    /**
     * @return return whether this year is a jalali leap year
     */
    val isLeap: Boolean
        get() = getLeapFactor(year) == 0

    val yearLength: Int
        get() = if (isLeap) 366 else 365

    val monthLength: Int
        get() {
            if (month < 7) {
                return 31
            } else if (month < 12) {
                return 30
            } else if (month == 12) {
                return if (isLeap)
                    30
                else
                    29
            }
            return 0
        }


    private val gregorianFirstFarvardin: GregorianCalendar
        get() {
            var marchDay = 0
            val breaks = intArrayOf(
                -61,
                9,
                38,
                199,
                426,
                686,
                756,
                818,
                1111,
                1181,
                1210,
                1635,
                2060,
                2097,
                2192,
                2262,
                2324,
                2394,
                2456,
                3178
            )

            val jalaliYear = year
            val gregorianYear = jalaliYear + 621
            var jalaliLeap = -14
            var jp = breaks[0]

            var jump: Int
            for (j in 1..19) {
                val jm = breaks[j]
                jump = jm - jp
                if (jalaliYear < jm) {
                    var N = jalaliYear - jp
                    jalaliLeap = jalaliLeap + N / 33 * 8 + (N % 33 + 3) / 4

                    if (jump % 33 == 4 && jump - N == 4)
                        jalaliLeap = jalaliLeap + 1

                    val GregorianLeap = gregorianYear / 4 - (gregorianYear / 100 + 1) * 3 / 4 - 150

                    marchDay = 20 + (jalaliLeap - GregorianLeap)

                    if (jump - N < 6)
                        N - jump + (jump + 4) / 33 * 33

                    break
                }

                jalaliLeap = jalaliLeap + jump / 33 * 8 + jump % 33 / 4
                jp = jm
            }

            return GregorianCalendar(gregorianYear, 2, marchDay)
        }

    /**
     * Today Jalali Date
     */
    constructor() {
        fromGregorian(GregorianCalendar())
    }

    /**
     * Create a ir.huri.jcal.JalaliCalendar object
     *
     * @param year  Jalali Year
     * @param month Jalali Month
     * @param day   Jalali Day
     */
    constructor(year: Int, month: Int, day: Int) {
        set(year, month, day)
    }


    /**
     * Create a ir.huri.jcal.JalaliCalendar object from gregorian calendar
     *
     * @param gc gregorian calendar object
     */
    constructor(gc: GregorianCalendar) {
        fromGregorian(gc)
    }

    /**
     * Convert current jalali date to gregorian date
     *
     * @return date converted gregorianDate
     */
    fun toGregorian(): GregorianCalendar {
        val julianDay = toJulianDay()
        return julianDayToGregorianCalendar(julianDay)
    }

    /**
     * set date from gregorian date
     *
     * @param gc input gregorian calendar
     */
    fun fromGregorian(gc: GregorianCalendar) {
        val jd = gregorianToJulianDayNumber(gc)
        fromJulianDay(jd)
    }

    /**
     * get Jalali date by day difference
     *
     * @param diff number of day diffrents
     * @return jalali calendar diffحزن
     */
    fun getDateByDiff(diff: Int): JalaliCalendar {
        val gc = toGregorian()
        gc.add(Calendar.DAY_OF_MONTH, diff)
        return JalaliCalendar(gc)
    }

    operator fun set(year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val that = other as JalaliCalendar?

        return year == that?.year && month == that.month && day == that.day
    }

    private fun gregorianToJulianDayNumber(gc: GregorianCalendar): Int {
        val gregorianYear = gc.get(GregorianCalendar.YEAR)
        val gregorianMonth = gc.get(GregorianCalendar.MONTH) + 1
        val gregorianDay = gc.get(GregorianCalendar.DAY_OF_MONTH)

        return 1461 * (gregorianYear + 4800 + (gregorianMonth - 14) / 12) / 4 + 367 * (gregorianMonth - 2 - 12 * ((gregorianMonth - 14) / 12)) / 12 - 3 * ((gregorianYear + 4900 + (gregorianMonth - 14) / 12) / 100) / 4 + gregorianDay - 32075 - (gregorianYear + 100100 + (gregorianMonth - 8) / 6) / 100 * 3 / 4 + 752
    }

    private fun julianToJulianDayNumber(jc: JulianCalendar): Int {
        val julianYear = jc.year
        val julianMonth = jc.month
        val JulianDay = jc.day

        return 1461 * (julianYear + 4800 + (julianMonth - 14) / 12) / 4 + 367 * (julianMonth - 2 - 12 * ((julianMonth - 14) / 12)) / 12 - 3 * ((julianYear + 4900 + (julianMonth - 14) / 12) / 100) / 4 + JulianDay - 32075
    }

    private fun julianDayToGregorianCalendar(JulianDayNumber: Int): GregorianCalendar {

        val j = 4 * JulianDayNumber + 139361631 + (4 * JulianDayNumber + 183187720) / 146097 * 3 / 4 * 4 - 3908
        val i = j % 1461 / 4 * 5 + 308

        val gregorianDay = i % 153 / 5 + 1
        val gregorianMonth = i / 153 % 12 + 1
        val gregorianYear = j / 1461 - 100100 + (8 - gregorianMonth) / 6

        return GregorianCalendar(gregorianYear, gregorianMonth - 1, gregorianDay)
    }

    private fun fromJulianDay(JulianDayNumber: Int) {
        val gc = julianDayToGregorianCalendar(JulianDayNumber)
        val gregorianYear = gc.get(GregorianCalendar.YEAR)

        var jalaliYear: Int
        val jalaliMonth: Int
        val jalaliDay: Int

        jalaliYear = gregorianYear - 621

        val gregorianFirstFarvardin = JalaliCalendar(jalaliYear, 1, 1).gregorianFirstFarvardin
        val JulianDayFarvardinFirst = gregorianToJulianDayNumber(gregorianFirstFarvardin)
        var diffFromFarvardinFirst = JulianDayNumber - JulianDayFarvardinFirst


        if (diffFromFarvardinFirst >= 0) {
            if (diffFromFarvardinFirst <= 185) {
                jalaliMonth = 1 + diffFromFarvardinFirst / 31
                jalaliDay = diffFromFarvardinFirst % 31 + 1
                set(jalaliYear, jalaliMonth, jalaliDay)
                return
            } else {
                diffFromFarvardinFirst = diffFromFarvardinFirst - 186
            }
        } else {
            diffFromFarvardinFirst = diffFromFarvardinFirst + 179
            if (getLeapFactor(jalaliYear) == 1)
                diffFromFarvardinFirst = diffFromFarvardinFirst + 1
            jalaliYear -= 1
        }


        jalaliMonth = 7 + diffFromFarvardinFirst / 30
        jalaliDay = diffFromFarvardinFirst % 30 + 1
        set(jalaliYear, jalaliMonth, jalaliDay)
    }

    private fun toJulianDay(): Int {
        val jalaliMonth = month
        val jalaliDay = day

        val gregorianFirstFarvardin = gregorianFirstFarvardin
        val gregorianYear = gregorianFirstFarvardin.get(Calendar.YEAR)
        val gregorianMonth = gregorianFirstFarvardin.get(Calendar.MONTH) + 1
        val gregorianDay = gregorianFirstFarvardin.get(Calendar.DAY_OF_MONTH)

        val julianFirstFarvardin = JulianCalendar(gregorianYear, gregorianMonth, gregorianDay)


        return julianToJulianDayNumber(julianFirstFarvardin) + (jalaliMonth - 1) * 31 - jalaliMonth / 7 * (jalaliMonth - 7) + jalaliDay - 1
    }

    private fun getLeapFactor(jalaliYear: Int): Int {
        var leap = 0
        val breaks = intArrayOf(
            -61,
            9,
            38,
            199,
            426,
            686,
            756,
            818,
            1111,
            1181,
            1210,
            1635,
            2060,
            2097,
            2192,
            2262,
            2324,
            2394,
            2456,
            3178
        )

        var jp = breaks[0]

        var jump: Int
        for (j in 1..19) {
            val jm = breaks[j]
            jump = jm - jp
            if (jalaliYear < jm) {
                var N = jalaliYear - jp

                if (jump - N < 6)
                    N = N - jump + (jump + 4) / 33 * 33

                leap = ((N + 1) % 33 - 1) % 4

                if (leap == -1)
                    leap = 4

                break
            }

            jp = jm
        }

        return leap
    }

    override fun toString(): String {
        return String.format("%04d-%02d-%02d", year, month, day)
    }

    override fun hashCode(): Int {
        var result = year
        result = 31 * result + month
        result = 31 * result + day
        return result
    }

    private inner class JulianCalendar(year: Int, month: Int, day: Int) {
        var year: Int = 0
            internal set
        var month: Int = 0
            internal set
        var day: Int = 0
            internal set

        init {
            this.year = year
            this.month = month
            this.day = day
        }
    }
}