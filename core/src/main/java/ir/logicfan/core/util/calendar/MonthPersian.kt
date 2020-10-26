package ir.logicfan.core.util.calendar

import org.jetbrains.annotations.NonNls

/**
 * Represent month in persian calendar (jalali)
 */
@Deprecated("use github library in @javaherisaber")
enum class MonthPersian {
    FARVARDIN,
    ORDIBEHESHT,
    KHORDAD,
    TIR,
    MORDAD,
    SHAHRIVAR,
    MEHR,
    ABAN,
    AZAR,
    DEY,
    BAHMAN,
    ESFAND;

    /**
     * Gets the month-of-year `int` value.
     *
     * The values are numbered following the ISO-8601 standard,
     * from 1 (Farvardin) to 12 (Esfand).
     */
    val value: Int
        get() = ordinal + 1

    /**
     * Gets the month-of-year in persian string format.
     *
     *
     * The string are persian values
     *
     * @return the month-of-year, from فروردین to اسفند
     */
    val toPersian: String
        get() = PERSIAN_MONTHS_FA[ordinal]

    /**
     * Gets the month-of-year in english string format.
     *
     *
     * The string are english values
     *
     * @return the month-of-year, from farvardin to esfand
     */
    val toEnglish: String
        get() = PERSIAN_MONTHS_EN[ordinal]

    companion object {
        private val CACHED_VALUES = values()

        @NonNls
        internal val PERSIAN_MONTHS_EN = arrayOf(
            "Farvardin",
            "Ordibehesht",
            "Khordad",
            "Tir",
            "Mordad",
            "Shahrivar",
            "Mehr",
            "Aban",
            "Azar",
            "Dey",
            "Bahman",
            "Esfand"
        )
        @NonNls
        internal val PERSIAN_MONTHS_FA = arrayOf(
            "فروردین",
            "اردیبهشت",
            "خرداد",
            "تیر",
            "مرداد",
            "شهریور",
            "مهر",
            "آبان",
            "آذر",
            "دی",
            "بهمن",
            "اسفند"
        )

        /**
         * Obtains an instance of `MonthPersian` from an `int` value.
         *
         * @param month the month-of-year to represent, from 1 (Farvardin) to 12 (Ordibehesht)
         */
        @Throws(IllegalArgumentException::class)
        fun of(month: Int): MonthPersian {
            if (month < 1 || month > 12) {
                throw IllegalArgumentException("Invalid value for MonthOfYear: $month")
            }
            return CACHED_VALUES[month - 1] // we subtract month with 1 because cached values is a zero based array
        }

        fun getMonthLength(monthPersian: MonthPersian, isLeapYear: Boolean): Int = with(monthPersian.value) {
            when {
                this < 7 -> 31
                this < 12 -> 30
                this == 12 -> if (isLeapYear) {
                    30
                } else {
                    29
                }
                else -> 0
            }
        }
    }
}
