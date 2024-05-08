import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class DiedFrame implements ActionListener {

    private final Settings settings;
    private final GameStuff gameStuff;
    private final JFrame gameFrame;
    private final JFrame frame;
    private final JPanel panel;
    private final Playsound playsound;

    public DiedFrame(Settings settings, GameStuff gameStuff, JFrame gameFrame) {
        this.settings = settings;
        this.gameStuff = gameStuff;
        this.gameFrame = gameFrame;

        playsound = new Playsound("Button.wav");

        frame = new JFrame("You died");
        panel = new JPanel();
        panel.setBackground(new Color(139, 90, 43));
        prepareFrame();

        panel.setLayout(null);
        panel.setVisible(true);

        JButton back = new JButton("Launcher");
        back.setFocusable(false);
        back.setBounds(125, 500, 250, 60);
        back.addActionListener(this);
        panel.add(back);

        JButton retry = new JButton("Retry");
        retry.setFocusable(false);
        retry.setBounds(125, 400, 250, 60);
        retry.addActionListener(this);
        panel.add(retry);

        JLabel letters = new JLabel("YOU DIED");
        letters.setBounds(100, 100, 300, 60);
        letters.setVisible(true);
        letters.setFont(new Font("Arial", Font.BOLD, 60));

        JLabel statsPoints = new JLabel("Points: " + gameStuff.getPoints());
        statsPoints.setBounds(125,270, 250, 30);
        statsPoints.setVisible(true);
        statsPoints.setFont(new Font("Arial", Font.ITALIC, 30));

        JLabel statsTime = new JLabel("Time: " + getTime(gameStuff.getTimeElapsed() - 1));
        statsTime.setBounds(125,300, 250, 30);
        statsTime.setVisible(true);
        statsTime.setFont(new Font("Arial", Font.ITALIC, 30));

        JLabel statsLevel = new JLabel("Level: " + gameStuff.getCurrentLevel());
        statsLevel.setBounds(125,240, 250, 30);
        statsLevel.setVisible(true);
        statsLevel.setFont(new Font("Arial", Font.ITALIC, 30));

        panel.add(letters);
        panel.add(statsTime);
        panel.add(statsLevel);
        panel.add(statsPoints);
        panel.add(retry);
        panel.add(back);

    }

    private String getTime(int timeElapsed) {

        String string = "";

        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        if (timeElapsed / 60 / 60 >= 1){
            hours = timeElapsed / 60 / 60;
            timeElapsed = timeElapsed - ((timeElapsed / 60/ 60) * 60 * 60);
        }
        if (timeElapsed / 60  >= 1){
            minutes = timeElapsed / 60;
            timeElapsed = timeElapsed - ((timeElapsed / 60) * 60);
        }
        if (timeElapsed >= 1){
            seconds=timeElapsed;
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
        return string;
    }

    private void prepareFrame() {

        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("Snake.png")));
            frame.setIconImage(image.getImage());
        } catch (NullPointerException e) {
            System.out.println("Image not found");
        }
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton button) {

            playsound.playSound();

            if (button.getText().equalsIgnoreCase("Retry")) {
                gameFrame.dispose();
                frame.dispose();
                prepareRetry();
            } else {
                gameFrame.dispose();
                frame.dispose();
                new LaunchFrame(settings, gameStuff);
            }
        }

    }

    private void prepareRetry() {
        gameStuff.setCurrentLevel(Levels.LEVEL1);
        gameStuff.setLives(5);
        gameStuff.setKeyAmount(0);
        gameStuff.setTimeElapsed(1);
        gameStuff.setPoints(0);
        new GameFrame(settings, gameStuff);
    }
}
