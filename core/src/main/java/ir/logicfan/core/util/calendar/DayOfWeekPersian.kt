package ir.logicfan.core.util.calendar

import org.jetbrains.annotations.NonNls

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

        @NonNls
        internal val PERSIAN_WEEKDAYS_EN =
            arrayOf("Yekshanbeh", "Doshanbeh", "Seshhanbeh", "Chaharshanbeh", "Panjshanbeh", "Jomeh", "Shanbeh")
        @NonNls
        internal val PERSIAN_WEEKDAYS_FA =
            arrayOf("یکشنبه", "دوشنبه", "سه شنبه", "چهارشنبه", "پنج شنبه", "جمعه", "شنبه")

        @Throws(IllegalArgumentException::class)
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
    }
}