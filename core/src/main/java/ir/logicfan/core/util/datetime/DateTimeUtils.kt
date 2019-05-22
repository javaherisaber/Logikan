package ir.logicfan.core.util.datetime

import java.util.*

object DateTimeUtils {

    private const val TIME_LABEL_FORMAT = "%s - %s"
    private const val DATE_LABEL_FORMAT = "%s/%s/%s"

    /**
     * both params must be at this format 18:37
     *
     * @return formatted string with this style: 08:00 - 10:00
     */
    @JvmStatic
    fun formatTimePeriod(startTime: String, endTime: String): String {
        return String.format(TIME_LABEL_FORMAT, endTime, startTime)
    }

    /**
     * @param dateStamp format is 2018-08-27
     * @return formatted string with this style: 1397/10/29
     */
    @JvmStatic
    fun formatDateWithJalali(dateStamp: String): String {
        val calendar = getJalaliCalendarFromDateStamp(dateStamp)
        return String.format(DATE_LABEL_FORMAT, calendar.year, calendar.month, calendar.day)
    }

    @JvmStatic
    @Throws(IllegalArgumentException::class)
    fun formatDateStringWithStamp(dateStamp: String): String {
        if (dateStamp.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}".toRegex())) {
            val calendar = getJalaliCalendarFromDateStamp(dateStamp)
            return calendar.dayOfWeekDayMonthString + " " + calendar.year.toString()
        } else {
            throw IllegalArgumentException()
        }
    }

    @JvmStatic
    fun getHourMinuteFromTime(time: String): String {
        return time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
    }

    @JvmStatic
    @Throws(IllegalArgumentException::class)
    private fun getJalaliCalendarFromDateStamp(dateStamp: String): JalaliCalendar {
        if (dateStamp.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}".toRegex())) {
            val parts = dateStamp.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return JalaliCalendar(
                GregorianCalendar(
                    Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2])
                )
            )
        } else {
            throw IllegalArgumentException()
        }
    }
}
