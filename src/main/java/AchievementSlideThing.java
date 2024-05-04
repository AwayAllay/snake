import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class AchievementSlideThing {
    private final JPanel panel;
    private final JLabel textLabel;
    private final int time;

    public AchievementSlideThing(String text, JPanel panel,
                                 Color textColor, Color backgroundColor, Color borderColor, int timeStaying) {
        this.panel = panel;
        this.time = timeStaying / 4;

        Border border = BorderFactory.createLineBorder(borderColor, 2);

        textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setBorder(border);
        textLabel.setBackground(backgroundColor);
        textLabel.setForeground(textColor);
        textLabel.setOpaque(true);
        textLabel.setBounds(panel.getWidth(), 80, 220, 60);

        panel.add(textLabel);
        panel.setComponentZOrder(textLabel, 0);
    }

    public void act(){

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
    }
}
