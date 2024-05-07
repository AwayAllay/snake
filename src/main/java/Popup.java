import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Popup {
    private final JPanel panel;
    private final JLabel textLabel;
    private final int time;
    private final GameStuff gameStuff;
    private int numberOfPopups;

    public Popup(String text, JPanel panel,
                 Color textColor, Color backgroundColor, Color borderColor, int timeStaying, GameStuff gameStuff, String untertitel) {
        this.panel = panel;
        this.time = timeStaying / 4;
        this.gameStuff = gameStuff;

        Border border = BorderFactory.createLineBorder(borderColor, 2);

        //Uses html formation to format the text on the label
        String titel = "<html><h1 style='font-size: 12px; font-family: Arial; color: #F0F0F0;'>" + text + "</h1>"
                + "<p style='font-size: 10px; font-family: Arial;'>" + untertitel + "</p></html>";

        textLabel = new JLabel(titel, SwingConstants.LEFT);
        textLabel.setBorder(border);
        textLabel.setBackground(backgroundColor);
        textLabel.setForeground(textColor);
        textLabel.setOpaque(true);
        gameStuff.getPopups().add(this);
        numberOfPopups = gameStuff.getPopups().size();

        if (gameStuff.getPopups().size() < 2) {
            textLabel.setBounds(panel.getWidth(), 85, 220, 60);
        } else {
            textLabel.setBounds(panel.getWidth(), 80 + ((gameStuff.getPopups().size() - 1) * 60) + (5 * (gameStuff.getPopups().size())), 220, 60);
        }

        System.out.println(gameStuff.getPopups().size());

        panel.add(textLabel);
        panel.setComponentZOrder(textLabel, 0);

        playSound("Swoosh.wav");


        act(textLabel);

        //TODO if 2 at same time one of them y++ u know:)
    }

    public void playSound(final String fileName) {
        try {

            File soundFile = new File(getClass().getResource(fileName).toURI());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float volume = -10.0f;
            control.setValue(volume);

            clip.start();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    clip.close();
                    cancel();
                }
            }, clip.getMicrosecondLength() / 1000);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void act(final JLabel popupGiven) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            private int counter = -120;

            @Override
            public void run() {
                if (counter <= 0) {
                    textLabel.setLocation(textLabel.getX() - 2, textLabel.getY());
                } else if (counter < time) {
                    //Do nothing
                } else if (counter >= time && counter < time + 100) {
                    textLabel.setLocation(textLabel.getX() + 3, textLabel.getY());
                } else if (counter >= time + 100) {
                    panel.remove(textLabel);
                    gameStuff.getPopups().remove(Popup.this);
                    cancel();
                } else {
                    cancel();
                }
                if (gameStuff.getPopups().size() < numberOfPopups) {
                    popupGiven.setLocation(popupGiven.getX(), 80 + ((gameStuff.getPopups().size() - 1) * 60) + (5 * (gameStuff.getPopups().size())));
                    numberOfPopups = gameStuff.getPopups().size();
                }
                counter++;
                System.out.println(gameStuff.getPopups().size());
            }
        }, 0, 4);

    }
}
