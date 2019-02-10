package ir.logicfan.core.util;

import java.util.GregorianCalendar;

public class DateTimeUtils {

    private static final String TIME_LABEL_FORMAT = "%s - %s";
    private static final String DATE_LABEL_FORMAT = "%s/%s/%s";

    /**
     * both params must be at this format 18:37
     *
     * @return formatted string with this style: 08:00 - 10:00
     */
    public static String formatTimePeriod(String startTime, String endTime) {
        return String.format(TIME_LABEL_FORMAT, endTime, startTime);
    }

    /**
     * @param dateStamp format is 2018-08-27
     * @return formatted string with this style: 1397/10/29
     */
    public static String formatDateWithJalali(String dateStamp) {
        JalaliCalendar calendar = getJalaliCalendarFromDateStamp(dateStamp);
        return String.format(DATE_LABEL_FORMAT, calendar.getYear(), calendar.getMonth(), calendar.getDay());

    }

    public static String formatDateStringWithStamp(String dateStamp) {
        if (dateStamp.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            JalaliCalendar calendar = getJalaliCalendarFromDateStamp(dateStamp);
            return calendar.getDayOfWeekDayMonthString()
                    .concat(" ")
                    .concat(String.valueOf(calendar.getYear()));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String getHourMinuteFromTime(String time) {
        return time.split(":")[0];
    }

    private static JalaliCalendar getJalaliCalendarFromDateStamp(String dateStamp) {
        if (dateStamp.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            String[] parts = dateStamp.split("-");
            JalaliCalendar calendar = new JalaliCalendar(new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2])));
            return calendar;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
