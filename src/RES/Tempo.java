package RES;

import javax.tools.Tool;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by alexpopa95 on 08/04/15.
 */
public class Tempo {

    private Timer timer;
    private boolean finito;
    private int seconds;


    public Tempo(int seconds) {
        this.seconds = seconds;
        Gioco.frame.setTempoMaxMin(seconds, 0);
        Gioco.frame.setTempoValue(seconds);
        int delay = 2000;
        int interval = 1000;
        finito = false;
        timer = new Timer();
        TimerTask timerTask = new TimerSchedulePeriod();

        //System.err.println("\t\tTempo iniziato!");
        timer.schedule(timerTask, delay, interval);
    }

    class TimerSchedulePeriod extends TimerTask {
        @Override
        public void run() {
            seconds--;
            //System.err.println("\t\t"+seconds);
            if(seconds == 0) {
                finito = true;
                stop();
                //System.err.println("\t\tTempo finito!");
            }
            Gioco.frame.setTempoValue(seconds);
        }
    }

    public boolean isFinito() {
        return finito;
    }

    public int getSeconds() {
        return seconds;
    }

    public void pause() {
        timer.cancel();
    }

    public void resume() {
        timer = new Timer();
        TimerTask timerTask = new TimerSchedulePeriod();
        timer.schedule(timerTask, 0, 1000 );
    }

    public void stop() {
        Gioco.frame.tempo.setValue(0);
        timer.cancel();
        timer.purge();
    }
}
