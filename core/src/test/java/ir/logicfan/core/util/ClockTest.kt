/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.util

import org.junit.Assert
import org.junit.Test

class ClockTest {

    @Test
    fun isTimePeriodsOverlap() {
        val firstStartTime = Clock(hour = 8)
        val firstEndTime = Clock(hour = 12)
        var secondStartTime = Clock(hour = 8)
        var secondEndTime = Clock(hour = 14)
        fun check(): Boolean = Clock.isTimePeriodsOverlap(firstStartTime, firstEndTime, secondStartTime, secondEndTime)
        Assert.assertTrue(check())
        secondStartTime = Clock(hour = 10)
        secondEndTime = Clock(hour = 12)
        Assert.assertTrue(check())
        secondStartTime = Clock(hour = 12)
        secondEndTime = Clock(hour = 14)
        Assert.assertFalse(check())
        secondStartTime = Clock(hour = 7)
        secondEndTime = Clock(hour = 10)
        Assert.assertTrue(check())
        secondStartTime = Clock(hour = 9)
        secondEndTime = Clock(hour = 11)
        Assert.assertTrue(check())
        secondStartTime = Clock(hour = 6)
        secondEndTime = Clock(hour = 8)
        Assert.assertFalse(check())
    }
}