import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class LaunchFrame implements ActionListener {

    private final JFrame frame;
    private final JLabel letter;
    private final JPanel panel;
    private final SnakeHead head;
    private final List<SnakeTail> tails = new ArrayList<>();
    private final GameStuff gameStuff;
    private final Settings settings;

    public LaunchFrame(final Settings settings, final GameStuff gameStuff) {

        this.settings = settings;
        this.gameStuff = gameStuff;

        frame = new JFrame("Launch game");

        letter = new JLabel();
        letter.setBounds(145, 100, 250, 60);
        letter.setVisible(true);
        letter.setFont(new Font("Arial", Font.BOLD, 60));

        try {
            ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("Snake.png")));
            frame.setIconImage(image.getImage());
        } catch (NullPointerException e) {
            System.out.println("Image not found");
        }

        panel = new JPanel(null);

        JButton button = new JButton("Play game!");
        button.setBounds(125, 500, 250, 60);
        button.setFocusable(false);
        button.addActionListener(this);

        JButton settingsButton = new JButton("Settings");
        settingsButton.setBounds(125, 400, 250, 60);
        settingsButton.setFocusable(false);
        settingsButton.addActionListener(this);

        panel.setBackground(new Color(139, 90, 43));
        panel.add(button);
        panel.add(settingsButton);
        panel.add(letter);

        head = new SnakeHead(-10, 300, 10, 10);
        head.setBackground(settings.getSkin().getHeadColor());

        frame.add(head);
        frame.add(panel);

        prepareLaunchFrame();
        playIntroAnimation();
    }

    /**
     * Prepares the main frame
     */
    private void prepareLaunchFrame() {

        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }


    /**
     * Starts to play the animation for the launcher intro
     */
    private void playIntroAnimation() {
        drawText();
        drawApples();
        snakeCrawl();
    }


    /**
     * Lets the LauncherFrameSnake crawl across the Frame
     */
    private void snakeCrawl() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = 0;

            @Override
            public void run() {

                if (counter < 50) {

                    testEaten();

                    SnakeTail tailpiece = new SnakeTail(head.getX(), head.getY(), 10, 10);
                    panel.add(tailpiece);
                    runRight();
                    tails.add(tailpiece);
                    tailpiece.setBackground(settings.getSkin().getTailColor());
                    counter++;

                } else {
                    deleteSnake();
                    cancel();
                }
            }
        }, 10000, 200);
    }

    /**
     * Starts to remove the Snake from the Frame
     */
    private void deleteSnake() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            int i = 0;

            @Override
            public void run() {
                if (i < tails.size()) {
                    panel.remove(tails.get(i));
                    panel.revalidate();
                    panel.repaint();
                    i++;
                } else {
                    tails.clear();
                    cancel();
                }
            }
        }, 0, 200);
    }


    /**
     * Looks if a boost gets eaten by the Sneak Head, and removes it if so
     */
    private void testEaten() {
        for (Component component : panel.getComponents()) {

            if (component instanceof DisplayedLaunchBoost) {

                JLabel label = (DisplayedLaunchBoost) component;

                if (component.getX() == head.getX()) {
                    panel.remove(label);
                    panel.revalidate();
                    panel.repaint();
                    break;
                }
            }
        }
    }


    /**
     * Secondly called by the playIntroAnimation Method, and draws a few boosts
     * across the LaunchFrame
     */
    private void drawApples() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int x = 40;
            int boostcount = 0;

            @Override
            public void run() {
                if (boostcount < 11) {
                    DisplayedLaunchBoost displayedLaunchBoost = new DisplayedLaunchBoost(x, 300, 10, 10, null);
                    panel.add(displayedLaunchBoost);
                    x += 40;
                    boostcount++;
                    panel.revalidate();
                    panel.repaint();
                } else {
                    cancel();
                }
            }
        }, 4000, 400);

    }


    /**
     * Firstly called by the playIntroAnimation Method, and lets the Word SNAKE appear char by char
     */
    private void drawText() {
        String word = "SNAKE";
        Timer wordTimer = new Timer();
        wordTimer.scheduleAtFixedRate(new TimerTask() {
            int index = 0;

            @Override
            public void run() {
                if (index < 5) {
                    letter.setText(letter.getText() + word.charAt(index));
                    index++;
                } else {
                    cancel();
                }
            }
        }, 1100, 200);
    }


    /**
     * Moves the head int positive x direction
     */
    private void runRight() {
        head.setLocation(head.getX() + 10, head.getY());
    }


    /**
     * Looks for the button to be clicked and calls the main Game Frame
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton klickedButton) {

            if (klickedButton.getText().equalsIgnoreCase("Play game!")) {
                frame.dispose();
                new GameFrame(settings, gameStuff);

            } else if (klickedButton.getText().equalsIgnoreCase("Settings")) {
                frame.dispose();
                new SettingsFrame(settings, gameStuff);
            }

        } else {
            System.out.println("Sth went wrong");
        }
    }

}
