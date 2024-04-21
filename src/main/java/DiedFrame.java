import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class DiedFrame implements ActionListener {

    private final Settings settings;
    private final GameStuff gameStuff;

    private final JFrame frame;

    public DiedFrame(Settings settings, GameStuff gameStuff) {
        this.settings = settings;
        this.gameStuff = gameStuff;

        frame = new JFrame("You died");
        prepareFrame();

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setVisible(true);

        JButton back = new JButton("Launcher");
        back.setFocusable(false);
        back.setBounds(125, 460, 250, 60);
        back.addActionListener(this);
        panel.add(back);

        JButton retry = new JButton("Retry");
        back.setFocusable(false);
        back.setBounds(125, 345, 250, 60);
        back.addActionListener(this);
        panel.add(back);

        JLabel letters = new JLabel("YOU DIED");
        letters.setBounds(145, 100, 250, 60);
        letters.setVisible(true);
        letters.setFont(new Font("Arial", Font.BOLD, 60));

        panel.add(letters);
        panel.add(retry);
        panel.add(back);
        frame.add(panel);

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
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton button){
            if (button.getText().equalsIgnoreCase("Retry")){
                frame.dispose();
                prepareRetry();
                new LevelEins(settings, gameStuff);
            }else {
                frame.dispose();
                new LaunchFrame(settings, gameStuff);
            }
        }

    }

    private void prepareRetry() {
        gameStuff.setCurrentLevel(Levels.LEVEL1);
        gameStuff.setLives(5);
        gameStuff.setKeyAmount(0);
    }
}
