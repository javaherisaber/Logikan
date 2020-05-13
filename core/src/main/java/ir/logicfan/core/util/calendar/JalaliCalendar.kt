package ir.logicfan.core.util.calendar

import android.annotation.SuppressLint
import ir.logicfan.core.util.Clock
import ir.logicfan.core.util.extension.toZeroTail
import java.io.Serializable
import java.text.MessageFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class JalaliCalendar(
    var year: Int, var month: MonthPersian, var day: Int, var clock: Clock
) : Serializable, Comparable<JalaliCalendar> {

    init {
        basicArgumentValidating(year, month.value, day)
    }

    val isLeapYear: Boolean
        get() = DateConverter().isJalaliLeapYear(year)
    val dayOfWeek: DayOfWeekPersian
        get() = DayOfWeekPersian.of(toGregorian().get(Calendar.DAY_OF_WEEK))
    val yearLength: Int
        get() = if (isLeapYear) 366 else 365
    val monthString: String
        get() = month.toPersian
    val dayOfWeekString: String
        get() = dayOfWeek.toPersian
    val monthLength: Int
        get() = MonthPersian.getMonthLength(month, isLeapYear)

    /**
     * Now as Jalali Date
     */
    constructor() : this(DateConverter().gregorianToJalali(GregorianCalendar()))

    constructor(jalali: JalaliCalendar) : this(jalali.year, jalali.month.value, jalali.day, jalali.clock)

    // use only with calendar which provides persian date
    constructor(calendar: Calendar) : this(
        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)
    )

    constructor(gregorian: GregorianCalendar) : this(DateConverter().gregorianToJalali(gregorian))

    constructor(year: Int, month: Int, day: Int) : this(year, month, day, Clock())

    constructor(year: Int, monthPersian: MonthPersian, day: Int) : this(year, monthPersian.value, day)

    constructor(year: Int, month: Int, day: Int, clock: Clock) : this(year, MonthPersian.of(month), day, clock)

    /**
     * validate primary constructor inputs
     */
    private fun basicArgumentValidating(year: Int, month: Int, day: Int) {
        if (day <= 0 || day > 31)
            throw IllegalArgumentException("Wrong value for day, it must be from 1 to 31")

        if (year <= 0)
            throw IllegalArgumentException("Wrong value for Year, it must be positive ")

        if (month >= 7 && day == 31)
            throw IllegalArgumentException("Wrong value for day. in second half of year, months have less than 31 days ")

        if (!isLeapYear && month == 12 && day >= 30)
            throw IllegalArgumentException("Wrong value for day, just in leap year last month must be greater than 29")
    }

    /**
     * convert current object to gregorian calendar
     */
    fun toGregorian(): GregorianCalendar = DateConverter().jalaliToGregorian(this)

    /**
     * Convert current calendar to Unix Epoch (seconds) timestamp
     */
    fun toUnixTimestamp(): String = (epochMillis() / 1000).toString()

    @SuppressLint("SimpleDateFormat")
    fun epochMillis(): Long {
        val gregorian = toGregorian()
        val year = gregorian.get(Calendar.YEAR)
        val month = gregorian.get(Calendar.MONTH) + 1 // gregorian calendar is zero based
        val day = gregorian.get(Calendar.DAY_OF_MONTH)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
        val date = dateFormat.parse("$year-$month-$day-${clock.hour}-${clock.minute}-${clock.second}")
        return date.time
    }

    @SuppressLint("SimpleDateFormat")
    fun formatToGregorian(pattern: String = "yyyy-MM-dd"): String {
        val date = Date(this.epochMillis())
        return SimpleDateFormat(pattern, Locale.US).format(date)
    }

    /**
     * Convert current calendar to Iso timestamp (yyyy-MM-dd HH:mm:ss)
     */
    @SuppressLint("SimpleDateFormat")
    fun toIsoTimestamp(): String {
        val gregorian = toGregorian()

        val year = gregorian.get(Calendar.YEAR)
        val month = gregorian.get(Calendar.MONTH) + 1 // gregorian calendar is zero based
        val day = gregorian.get(Calendar.DAY_OF_MONTH)

        return "$year-${month.toZeroTail()}-${day.toZeroTail()} ${this.clock}"
    }

    /**
     * type 1 is the following format:
     * 12 اردیبهشت 1395
     */
    private fun getDateLabelLetterMonth(): String = MessageFormat.format(
        "{0} {1} {2,number,#}",
        this.day,
        this.monthString,
        this.year
    )

    /**
     * type 2 is the following format:
     * پنجشنبه 12 تیر 1398
     */
    private fun getDateLabelLetterMonthPlusDay(): String = MessageFormat.format(
        "{0} {1} {2} {3,number,#}",
        this.dayOfWeekString,
        this.day,
        this.monthString,
        this.year
    )

    /**
     * type 3 is the following format:
     * 1398/04/12
     */
    private fun getDateLabelDigit(): String = MessageFormat.format(
        "{0,number,#}/{1}/{2}",
        this.year,
        this.month.value,
        this.day
    )

    override fun compareTo(other: JalaliCalendar): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month.value - other.month.value
        day != other.day -> day - other.day
        else -> clock.compareTo(other.clock)
    }

    /**
     * To be used by [getDateLabel] method to return suitable labeled jalali calendar
     */
    enum class JalaliLabelType {
        LETTER_MONTH, LETTER_MONTH_PLUS_DAY, DIGIT
    }

    companion object {

        /**
         * Get current time as Unix epoch timestamp
         */
        @JvmStatic
        fun currentUnixTimestamp(): Long = System.currentTimeMillis() / 1000

        /**
         * Calculate difference between given unix timestamp and now
         * (unix timestamp is simply seconds passed since 1970)
         *  @return seconds
         */
        @JvmStatic
        fun timestampDifferenceSeconds(unixTimestamp: Long): Int = (unixTimestamp - currentUnixTimestamp()).toInt()

        @JvmStatic
        fun getDateLabel(jalaliCalendar: JalaliCalendar, type: JalaliLabelType): String = when (type) {
            JalaliLabelType.LETTER_MONTH -> jalaliCalendar.getDateLabelLetterMonth()
            JalaliLabelType.LETTER_MONTH_PLUS_DAY -> jalaliCalendar.getDateLabelLetterMonthPlusDay()
            JalaliLabelType.DIGIT -> jalaliCalendar.getDateLabelDigit()
        }

        /**
         * @param unixTimestamp Unix Timestamp with this format : 1561783493
         * @return [JalaliCalendar] object is timestamp is correct or null otherwise
         */
        @JvmStatic
        fun getJalaliCalendarFromUnixTimestamp(unixTimestamp: Long): JalaliCalendar {
            val date = Date(unixTimestamp * 1000L)
            val gregorian = GregorianCalendar().apply {
                time = date
            }
            return JalaliCalendar(gregorian)
        }

        @SuppressLint("SimpleDateFormat")
        @JvmStatic
        @Throws(ParseException::class)
        fun getJalaliCalendarFromIsoTimestamp(isoTimestamp: String, format: String = "yyyy-MM-dd HH:mm:ss"): JalaliCalendar {
            val date = SimpleDateFormat(format).parse(isoTimestamp)
            val gregorian = GregorianCalendar().apply {
                time = date
            }
            return JalaliCalendar(gregorian)
        }

        /**
         * @return JalaliCalendar from date : 2019-12-11
         */
        @JvmStatic
        fun getJalaliCalendarFromDate(date: String): JalaliCalendar {
            val parts = date.split("-")
            val gregorian = GregorianCalendar(parts[0].toInt(), parts[1].toInt() - 1, parts[2].toInt())
            return JalaliCalendar(gregorian)
        }

        @JvmStatic
        fun today(clock: Clock = Clock()): JalaliCalendar {
            val calendar = JalaliCalendar()
            calendar.clock = clock
            return calendar
        }

        @JvmStatic
        fun isTimeStampExpired(unixTimestamp: Long?): Boolean {
            unixTimestamp?.let {
                val timeStampDate = getJalaliCalendarFromUnixTimestamp(it)
                val today = JalaliCalendar()
                return today >= timeStampDate
            }
            return false
        }
    }
}