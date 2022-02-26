package timestables;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {

    private final Timer timer;
    private int seconds;

    public Stopwatch(Timer timer) {
        this.timer = timer;
        this.seconds = 0;
    }

    public void start(JLabel label, Utility utility) {
        TimerTask task = new TimerTask() {

            public void run() {
                seconds++;
                label.setText(utility.getMinutesAndSecondsTimer(seconds));
            }
        };

        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public void stop() {
        timer.cancel();
    }

    public int getSeconds() {
        return seconds;
    }
}
