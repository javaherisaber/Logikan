/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.util.calendar

import ir.logicfan.core.util.Clock
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
        assertEquals(JalaliCalendar.getJalaliCalendarFromUnixTimestamp(TIMESTAMP), jalaliCalendar)
    }

    @Test
    fun getJalaliCalendarFromUnixTimestamp() {
        val calendar = JalaliCalendar(1398, 10, 24, Clock(16, 41, 41))
        assertEquals(JalaliCalendar.getJalaliCalendarFromUnixTimestamp(1579007501), calendar)
    }

    @Test
    fun getJalaliCalendarFromIsoTimestamp() {
        val calendar = JalaliCalendar(1398, 10, 24, Clock(16, 41, 41))
        assertEquals(JalaliCalendar.getJalaliCalendarFromIsoTimestamp("2020-01-14 16:41:41"), calendar)
    }

    companion object {
        const val TIMESTAMP = 1561836600L // corresponding to 1398/04/09
    }
}