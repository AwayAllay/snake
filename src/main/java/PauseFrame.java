import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PauseFrame extends JFrame implements ActionListener {

    /**
     * The displayed frame
     */
    private final JFrame frame;

    private final Settings settings;

    private final KeyListener keyListener;
    private final GameStuff gameStuff;
    private final JFrame gameFrame;

    public PauseFrame(Settings settings, KeyListener keyListener, JFrame gameFrame, GameStuff gameStuff) {

        this.gameStuff = gameStuff;
        this.settings = settings;
        this.keyListener = keyListener;
        this.gameFrame = gameFrame;

        frame = new JFrame("Pause menu");
        prepareLaunchFrame();

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel letter = new JLabel("Paused");
        letter.setBounds(145, 100, 250, 60);
        letter.setVisible(true);
        letter.setFont(new Font("Arial", Font.BOLD, 60));
        panel.add(letter);

        JButton continueGame = new JButton("Continue");
        continueGame.setFocusable(false);
        continueGame.setBounds(125, 345, 250, 60);
        continueGame.addActionListener(this);
        panel.add(continueGame);

        JButton backToLauncher = new JButton("Launcher");
        backToLauncher.setFocusable(false);
        backToLauncher.setBounds(125, 460, 250, 60);
        backToLauncher.addActionListener(this);
        panel.add(backToLauncher);


        frame.add(panel);

    }

    /**
     * Prepares the frame(Sets size, image, etc)
     */
    private void prepareLaunchFrame() {

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

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton button) {

            if (button.getText().equalsIgnoreCase("Launcher")) {
                frame.setVisible(false);
                gameFrame.setVisible(false);
                int answer = JOptionPane.showConfirmDialog(null, "Leaving will take you to the launcher. Your stats WILL NOT BE SAVED!", "Sure?", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    frame.dispose();
                    gameFrame.dispose();
                    gameStuff.setTimeElapsed(1);
                    gameStuff.setKeyAmount(0);
                    gameStuff.setPoints(0);
                    gameStuff.setCurrentLevel(Levels.LEVEL1);
                    gameStuff.setLives(5);
                    new LaunchFrame(settings, gameStuff);
                }else {
                    frame.setVisible(true);
                    gameFrame.setVisible(true);
                }
            } else {
                frame.dispose();
                keyListener.resumeTimer();
            }

        }

    }
}
