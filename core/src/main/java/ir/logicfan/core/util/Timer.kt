package ir.logicfan.core.util

import android.os.Handler

class Timer(private var seconds: Int, private val listener: TimerListener?) {

    companion object {
        private const val DELAY_MILLIS = 1000L
    }

    interface TimerListener {
        fun onTimerExpired()
        fun onTimerElapsed(secondsLeft: Int)
    }

    fun start() {
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                if (seconds == 0) {
                    listener?.onTimerExpired()
                } else {
                    seconds -= 1
                    listener?.onTimerElapsed(seconds)
                    handler.postDelayed(this, DELAY_MILLIS)  // execute run() in the future
                }
            }
        })
    }

    fun stop() {
        this.seconds = 0
    }
}
