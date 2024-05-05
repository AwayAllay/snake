import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class AchievementSlideThing {
    private final JPanel panel;
    private final JLabel textLabel;
    private final int time;
    private final GameStuff gameStuff;
    private int numberOfPopups;

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
        gameStuff.getPopups().add(this);
        numberOfPopups = gameStuff.getPopups().size();
        if(gameStuff.getPopups().size() < 2) {
            textLabel.setBounds(panel.getWidth(), 85, 220, 60);
        }else {
            //gameStuff.getPopups().size()
            textLabel.setBounds(panel.getWidth(), 80 + ((gameStuff.getPopups().size() -1) * 60) + (5 * (gameStuff.getPopups().size())), 220, 60);
        }

        System.out.println(gameStuff.getPopups().size());

        panel.add(textLabel);
        panel.setComponentZOrder(textLabel, 0);

        act(textLabel);
        //TODO if 2 at same time one of them y++ u know:)
    }

    private void act(final JLabel popupGiven){

        Timer timer = new Timer();
        JLabel popup = popupGiven;

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
                    gameStuff.getPopups().remove(AchievementSlideThing.this);
                    cancel();
                }
                else{
                    cancel();
                }
                if (gameStuff.getPopups().size() < numberOfPopups){
                    int bla = numberOfPopups - gameStuff.getPopups().size();
                    //popupGiven.setLocation(popupGiven.getX(), );
                    numberOfPopups = gameStuff.getPopups().size();
                }
                counter++;
                System.out.println(gameStuff.getPopups().size());
            }
        }, 0, 4);

        /*Timer removal = new Timer();
        removal.schedule(new TimerTask() {
            @Override
            public void run() {
                gameStuff.getPopups().remove(AchievementSlideThing.this);
                cancel();
                System.out.println(gameStuff.getPopups().size());
            }
        }, time + 300);
        System.out.println(gameStuff.getPopups().size());*/
    }
}
