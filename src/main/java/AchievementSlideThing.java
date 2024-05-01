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
    private final Color textColor;
    private final Color backgroundColor;
    private final Color borderColor;

    public AchievementSlideThing(Settings settings, GameStuff gameStuff, String text, JPanel panel,
                                 Color textColor, Color backgroundColor, Color borderColor) {
        this.settings = settings;
        this.gameStuff = gameStuff;
        this.text = text;
        this.panel = panel;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;

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

            private int counter = 0;
            @Override
            public void run() {
                if (counter < 240) {
                    textLabel.setLocation(textLabel.getX() - 1, textLabel.getY());
                } else if (counter > 1000 && counter < 1700) {
                    textLabel.setLocation(textLabel.getX() + 1, textLabel.getY());
                }else if (counter > 1700){
                    panel.remove(textLabel);
                    cancel();
                }
                counter++;
            }
        }, 0, 4);
    }
}
