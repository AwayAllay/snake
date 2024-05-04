import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AchievementSlideThing {
    private final JPanel panel;
    private final JLabel textLabel;
    private final int time;
    private final GameStuff gameStuff;

    public AchievementSlideThing(String text, JPanel panel,
                                 Color textColor, Color backgroundColor, Color borderColor, int timeStaying , GameStuff gameStuff) {
        this.panel = panel;
        this.time = timeStaying / 4;
        this.gameStuff = gameStuff;

        Border border = BorderFactory.createLineBorder(borderColor, 2);

        textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setBorder(border);
        textLabel.setBackground(backgroundColor);
        textLabel.setForeground(textColor);
        textLabel.setOpaque(true);
        if(gameStuff.getPopups().size() == 1) {
            textLabel.setBounds(panel.getWidth(), 80, 220, 60);
            gameStuff.getPopups().add(this);
        }else {
            textLabel.setBounds(panel.getWidth(), 80 + gameStuff.getPopups().size() * 60, 220, 60);
            gameStuff.getPopups().add(this);
        }

        System.out.println(gameStuff.getPopups().size());

        panel.add(textLabel);
        panel.setComponentZOrder(textLabel, 0);

        act();
        //TODO if 2 at same time one of them y++ u know:)
    }

    private void act(){

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            private int counter = -240;
            @Override
            public void run() {
                if (counter <= 0) {
                    textLabel.setLocation(textLabel.getX() - 1, textLabel.getY());
                }
                else if (counter < time) {
                    //Do nothing
                }
                else if (counter >= time && counter < time + 300) {
                    textLabel.setLocation(textLabel.getX() + 1, textLabel.getY());
                }
                else if (counter >= time + 300) {
                    panel.remove(textLabel);
                    cancel();
                }
                else{
                    cancel();
                }
                counter++;
            }
        }, 0, 4);

        Timer removal = new Timer();
        removal.schedule(new TimerTask() {
            @Override
            public void run() {
                gameStuff.getPopups().remove(AchievementSlideThing.this);
                cancel();
                System.out.println(gameStuff.getPopups().size());
            }
        }, time + 300);
        System.out.println(gameStuff.getPopups().size());
    }
}
