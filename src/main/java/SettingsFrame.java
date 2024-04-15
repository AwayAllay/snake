import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SettingsFrame extends JFrame implements ActionListener {

    /**The displayed frame*/
    private final JFrame frame;

    public SettingsFrame() {

        frame = new JFrame("Settings");
        prepareLaunchFrame();

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

    /**Prepares the frame(Sets size, image, etc)*/
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

    /**Listener for the different buttons*/
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton button) {

            if (button.getText().equalsIgnoreCase("Difficulty")) {
                frame.dispose();
                setDifficulty();
            } else if (button.getText().equalsIgnoreCase("Cosmetics")) {
                frame.dispose();
                new Skins();
            } else if (button.getText().equalsIgnoreCase("Back")) {
                frame.dispose();
                new LaunchFrame();
            } else if (button.getText().equalsIgnoreCase("Game-rules")) {
                frame.dispose();
                new GameRuleFrame();
            }
        }

    }

    /**Gets an input difficulty as String and puts it, if valid, in Lowercase as value for the String mode*/
    private void setDifficulty() {

        String choice = JOptionPane.showInputDialog("Select Difficulty: \n <Noob, Beginner, Adult, Master, God> \n current mode: " + Main.getMode());
        String mode;

        if (choice != null) {
            if (choice.equalsIgnoreCase("Noob")
                    || choice.equalsIgnoreCase("Beginner")
                    || choice.equalsIgnoreCase("Adult")
                    || choice.equalsIgnoreCase("Master")
                    || choice.equalsIgnoreCase("God")) {
                mode = choice.toLowerCase();
                Main.setMode(mode);
                System.out.println(mode);
            } else {
                JOptionPane.showMessageDialog(null, "That is NOT a selectable difficulty!", "Not a difficulty!", JOptionPane.WARNING_MESSAGE);
            }
        }
        new SettingsFrame();
    }
}
