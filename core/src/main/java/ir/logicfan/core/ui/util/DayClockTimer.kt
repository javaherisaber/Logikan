/*
 * Copyright (C) Logicfan, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 * Written by Mahdi Javaheri Saber <m.javaherisaber@gmail.com>, Mohammad Hosein Abedini <mohammadhoseinabedini@gmail.com>  2020.
 */

package ir.logicfan.core.ui.util

import ir.logicfan.core.util.Clock

class DayClockTimer(seconds: Int, private val listener: DayClockTimerListener?) : Timer.TimerListener {

    private var _day: Int = seconds.div(DAY_DURATION_SECONDS)
    val day = _day
    val clock: Clock = Clock(seconds.rem(DAY_DURATION_SECONDS))

    private val timer: Timer = Timer(seconds, this)

    fun start() {
        timer.start()
    }

    fun stop() {
        timer.stop()
    }

    override fun onTimerExpired() {
        _day = 0
        listener?.onDayElapsed(_day)
        clock.setMin()
        listener?.onClockElapsed(clock)
    }

    override fun onTimerElapsed(secondsLeft: Int) {
        if (clock.isMin()) {
            // one day elapsed
            _day--
            listener?.onDayElapsed(_day)
            clock.setMax()
            listener?.onClockElapsed(clock)
        } else {
            // clock elapsed
            clock.countDownSecond()
            listener?.onClockElapsed(clock)
        }
    }

    companion object {
        const val DAY_DURATION_SECONDS = 86400
    }

    interface DayClockTimerListener {
        fun onClockElapsed(clock: Clock)
        fun onDayElapsed(day: Int)
    }
}