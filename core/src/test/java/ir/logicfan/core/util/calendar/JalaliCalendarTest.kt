package ir.logicfan.core.util.calendar

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class JalaliCalendarTest {

    var jalaliCalendar = JalaliCalendar(1398, MonthPersian.TIR, 9)

    @Test
    fun isLeapYear() {
        jalaliCalendar.year = 1398
        assertFalse(jalaliCalendar.isLeapYear)

        jalaliCalendar.year = 1399
        assertTrue(jalaliCalendar.isLeapYear)

        jalaliCalendar.year = 1243
        assertTrue(jalaliCalendar.isLeapYear)
    }

    @Test
    fun getDayOfWeek() {
        assertEquals(jalaliCalendar.dayOfWeek, DayOfWeekPersian.Yekshanbeh)
    }

    @Test
    fun getMonthString() {
        jalaliCalendar.month = MonthPersian.TIR
        assertEquals(jalaliCalendar.monthString, MonthPersian.TIR.toPersian)
    }

    @Test
    fun getDayOfWeekString() {
        assertEquals(jalaliCalendar.dayOfWeekString, DayOfWeekPersian.Yekshanbeh.toPersian)
    }

    @Test
    fun toGregorian() {
        val gregorianCalendar = jalaliCalendar.toGregorian()
        assertEquals(gregorianCalendar, GregorianCalendar(2019, Calendar.JUNE, 30))
    }

    @Test
    fun toUnixTimestamp() {
        assertEquals(jalaliCalendar.toUnixTimestamp(), TIMESTAMP)
    }

    @Test
    fun getJalaliCalendar() {
        assertEquals(JalaliCalendar.getJalaliCalendar(TIMESTAMP), jalaliCalendar)
    }

    companion object {
        const val TIMESTAMP = "1561836600" // corresponding to 1398/04/09
    }
}