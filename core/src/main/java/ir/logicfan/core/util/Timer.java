package ir.logicfan.core.util;

import android.os.Handler;

public class Timer {

    private int seconds;
    private TimerListener listener;
    private static final int DELAY_MILLIS = 1000;

    public interface TimerListener {
        void onTimerExpired();
        void onTimerElapsed(int secondsLeft);
    }

    public Timer(int seconds, TimerListener listener) {
        this.seconds = seconds;
        this.listener = listener;
    }

    public void start() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (seconds == 0) {
                    if (listener != null) {
                        listener.onTimerExpired();
                    }
                } else {
                    seconds -= 1;
                    if (listener != null) {
                        listener.onTimerElapsed(seconds);
                    }
                    handler.postDelayed(this, DELAY_MILLIS);  // execute run() in the future
                }
            }
        });
    }
}
