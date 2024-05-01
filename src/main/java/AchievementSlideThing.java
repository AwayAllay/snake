import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class AchievementSlideThing {

    private final Settings settings;
    private final GameStuff gameStuff;
    private final String text;
    private final JPanel panel;
    private final JLabel textLabel;

    public AchievementSlideThing(Settings settings, GameStuff gameStuff, String text, JPanel panel) {
        this.settings = settings;
        this.gameStuff = gameStuff;
        this.text = text;
        this.panel = panel;

        Border border = BorderFactory.createLineBorder(Color.GREEN, 2);

        textLabel = new JLabel(text);
        textLabel.setBorder(border);
        textLabel.setBackground(new Color(96, 96,96));
        textLabel.setForeground(Color.WHITE);
        textLabel.setOpaque(true);
        textLabel.setBounds(panel.getWidth(), 80, 180, 60);

        panel.add(textLabel);
    }

    public void act(){

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            private int counter = 0;
            @Override
            public void run() {
                if (counter < 200) {
                    textLabel.setLocation(textLabel.getX() - 1, textLabel.getY());
                } else if (counter > 700 && counter < 1000) {
                    textLabel.setLocation(textLabel.getX() + 1, textLabel.getY());
                }else if (counter > 1000){
                    panel.remove(textLabel);
                    cancel();
                }
                counter++;
            }
        }, 0, 10);
    }
}
