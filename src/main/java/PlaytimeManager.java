import java.util.Timer;
import java.util.TimerTask;

public class PlaytimeManager {

    private Timer timer;
    private int time;
    private final GameStuff gameStuff;

    public PlaytimeManager(GameStuff gameStuff) {
        this.gameStuff = gameStuff;
    }

    public void startTimer(final int pTime, final Runnable runnable){

        timer = new Timer();

        time = pTime;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                runnable.run();
                time++;

            }
        }, 0, 1000);



    }

    public void stopTimer(){

        if (timer != null){
            gameStuff.setTimeElapsed(time);
            timer.cancel();
        }

    }


}
