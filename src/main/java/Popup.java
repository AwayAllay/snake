import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Popup {
    private final JPanel panel;
    private final JLabel textLabel;
    private final int time;
    private final GameStuff gameStuff;
    private int numberOfPopups;

    public Popup(String text, JPanel panel,
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
        timer.schedule(new TimerTask() {

            private int counter = -120;
            @Override
            public void run() {
                if (counter <= 0) {
                    textLabel.setLocation(textLabel.getX() - 2, textLabel.getY());
                }
                else if (counter < time) {
                    //Do nothing
                }
                else if (counter >= time && counter < time + 100) {
                    textLabel.setLocation(textLabel.getX() + 3, textLabel.getY());
                }
                else if (counter >= time + 100) {
                    panel.remove(textLabel);
                    gameStuff.getPopups().remove(Popup.this);
                    cancel();
                }
                else{
                    cancel();
                }
                if (gameStuff.getPopups().size() < numberOfPopups){
                    popupGiven.setLocation(popupGiven.getX(), 80 + ((gameStuff.getPopups().size() -1) * 60) + (5 * (gameStuff.getPopups().size())));
                    numberOfPopups = gameStuff.getPopups().size();
                }
                counter++;
                System.out.println(gameStuff.getPopups().size());
            }
        }, 0, 4);

    }
}
