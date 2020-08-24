package ir.logicfan.core.ui.util

import android.os.Handler

@Deprecated("We can use android os built-in class from android.os.CountDownTimer see " +
        "https://github.com/google-developer-training/android-kotlin-fundamentals-apps/blob/" +
        "master/GuessTheWordTransformation/app/src/main/java/com/example/android/guesstheword" +
        "/screens/game/GameViewModel.kt")
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
                when (seconds) {
                    -1 -> return
                    0 -> listener?.onTimerExpired()
                    else -> {
                        seconds -= 1
                        listener?.onTimerElapsed(seconds)
                        handler.postDelayed(this, DELAY_MILLIS)  // execute run() in the future
                    }
                }
            }
        })
    }

    fun stop() {
        this.seconds = -1
    }
}
