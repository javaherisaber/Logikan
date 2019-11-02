package ir.logicfan.core.util.calendar

/**
 * Represent day of week in persian calendar (jalali)
 */
enum class DayOfWeekPersian {
    Yekshanbeh,
    Doshanbeh,
    Seshhanbeh,
    Chaharshanbeh,
    Panjshanbeh,
    Jomeh,
    Shanbeh;

    /**
     * Gets the days-of-week `int` value.
     *
     * The values are numbered following the ISO-8601 standard,
     * from 1 (Yekshanbeh) to 7 (Shanbeh).
     */
    val value: Int
        get() = ordinal + 1

    val toPersian: String
        get() = PERSIAN_WEEKDAYS_FA[ordinal]

    val toEnglish: String
        get() = PERSIAN_WEEKDAYS_EN[ordinal]

    companion object {

        val PERSIAN_WEEKDAYS_EN =
            arrayOf("Yekshanbeh", "Doshanbeh", "Seshhanbeh", "Chaharshanbeh", "Panjshanbeh", "Jomeh", "Shanbeh")
        val PERSIAN_WEEKDAYS_FA = arrayOf("یکشنبه", "دوشنبه", "سه شنبه", "چهارشنبه", "پنج شنبه", "جمعه", "شنبه")

        val valuesPersian =
            arrayOf(Shanbeh, Yekshanbeh, Doshanbeh, Seshhanbeh, Chaharshanbeh, Panjshanbeh, Jomeh)

        @Throws(IllegalArgumentException::class)
        @JvmStatic
        fun of(value: Int): DayOfWeekPersian {
            if (value < 1 || value > 7) {
                throw IllegalArgumentException("Invalid day of week for : $value")
            }
            for (dayOfWeekPersian in values()) {
                if (dayOfWeekPersian.value == value)
                    return dayOfWeekPersian
            }
            throw IllegalArgumentException("Invalid day of week for : $value")
        }

        const val firstPersianDayValue = 7
        const val lastPersianDayValue = 1
    }
}