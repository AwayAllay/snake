import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SettingsFrame extends JFrame implements ActionListener {

    /**
     * The displayed frame
     */
    private final JFrame frame;
    private final GameStuff gameStuff;
    private final Settings settings;

    public SettingsFrame(final Settings settings, final GameStuff gameStuff) {

        this.settings = settings;
        this.gameStuff = gameStuff;

        frame = new JFrame("Settings");
        prepareFrame();

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton difficulty = new JButton("Difficulty");
        difficulty.setFocusable(false);
        difficulty.setBounds(125, 230, 250, 60);
        difficulty.addActionListener(this);
        panel.add(difficulty);

        JButton gamerules = new JButton("Game-rules");
        gamerules.setFocusable(false);
        gamerules.setBounds(125, 115, 250, 60);
        gamerules.addActionListener(this);
        panel.add(gamerules);

        JButton cosmetic = new JButton("Cosmetics");
        cosmetic.setFocusable(false);
        cosmetic.setBounds(125, 345, 250, 60);
        cosmetic.addActionListener(this);
        panel.add(cosmetic);

        JButton back = new JButton("Back");
        back.setFocusable(false);
        back.setBounds(125, 460, 250, 60);
        back.addActionListener(this);
        panel.add(back);

        frame.add(panel);

    }

    /**
     * Prepares the frame(Sets size, image, etc)
     */
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

    }

    /**
     * Listener for the different buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton button) {

            if (button.getText().equalsIgnoreCase("Difficulty")) {
                frame.dispose();
                setDifficulty();
            } else if (button.getText().equalsIgnoreCase("Cosmetics")) {
                frame.dispose();
                new Cosmetics(settings, gameStuff);
            } else if (button.getText().equalsIgnoreCase("Back")) {
                frame.dispose();
                new LaunchFrame(new SettingsManager().load(), gameStuff);
            } else if (button.getText().equalsIgnoreCase("Game-rules")) {
                frame.dispose();
                new GameRuleFrame();
            }
        }

    }

    /**
     * Gets an input difficulty as String
     */
    private void setDifficulty() {

        String choice = JOptionPane.showInputDialog("Select Difficulty: \n <" + Modes.NOOB + ", " + Modes.BEGINNER + ", " + Modes.ADULT + ", " + Modes.MASTER + ", " + Modes.GOD + "> \n current mode: " + settings.getMode());
        String mode;

        if (choice != null) {
            if (choice.equalsIgnoreCase("Noob")
                    || choice.equalsIgnoreCase("Beginner")
                    || choice.equalsIgnoreCase("Adult")
                    || choice.equalsIgnoreCase("Master")
                    || choice.equalsIgnoreCase("God")) {
                mode = choice.toUpperCase();
                settings.setMode(Modes.valueOf(mode));
                System.out.println(settings.getMode());
                new SettingsManager().save(settings);
            } else {
                JOptionPane.showMessageDialog(null, "That is NOT a selectable difficulty!", "Not a difficulty!", JOptionPane.WARNING_MESSAGE);
            }
        }
        new SettingsFrame(settings, gameStuff);
    }
}
