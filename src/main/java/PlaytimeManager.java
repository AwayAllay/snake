import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class PlaytimeManager {

    private Timer timer;
    private int time;
    private final GameStuff gameStuff;
    private final JLabel timerLabel;

    public PlaytimeManager(GameStuff gameStuff, JLabel timerLabel) {
        this.gameStuff = gameStuff;
        this.timerLabel = timerLabel;
    }

    public void startTimer(){

        timer = new Timer();

        time = gameStuff.getTimeElapsed();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                setTime(time);
                gameStuff.setTimeElapsed(time);
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

    private void setTime(int timeToSet) {

        String string = "";

        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        if (timeToSet / 60 / 60 >= 1){
            hours = timeToSet / 60 / 60;
            timeToSet = timeToSet - ((timeToSet / 60/ 60) * 60 * 60);
        }
        if (timeToSet / 60  >= 1){
            minutes = timeToSet / 60;
            timeToSet = timeToSet - ((timeToSet / 60) * 60);
        }
        if (timeToSet >= 1){
            seconds=timeToSet;
        }
        if (hours<=9){
            string = string + "0" + hours + ":";
        }else {
            string = string + hours + ":";
        }
        if (minutes<=9){
            string = string + "0" + minutes + ":";
        }else {
            string = string + minutes + ":";
        }
        if (seconds<=9){
            string = string + "0" + seconds;
        }else {
            string = string + seconds;
        }

        timerLabel.setText(string);

    }

    public int getTime() {
        return time;
    }
}
