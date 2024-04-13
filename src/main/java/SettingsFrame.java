import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SettingsFrame extends JFrame implements ActionListener {

    private final JFrame frame;
    private final JButton difficulty;

    private final JPanel panel;

    private final JButton back;

    private final JButton cosmetic;

    public SettingsFrame(){

        frame = new JFrame("Settings");
        prepareLaunchFrame();

        panel = new JPanel();

        difficulty = new JButton("Difficulty");
        difficulty.setFocusable(false);
        difficulty.setBounds(125, 400, 250, 60);
        difficulty.addActionListener(this);
        panel.add(difficulty);

        cosmetic = new JButton("Cosmetics");
        cosmetic.setFocusable(false);
        cosmetic.setBounds(125, 400, 250, 60);
        cosmetic.addActionListener(this);
        panel.add(cosmetic);

        back = new JButton("Back");
        back.setFocusable(false);
        back.setBounds(125, 400, 250, 60);
        back.addActionListener(this);
        panel.add(back);

        frame.add(panel);

    }

    private void prepareLaunchFrame(){

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

        if (e.getSource() instanceof JButton button){

            if (button.getText().equalsIgnoreCase("Difficulty")){

            } else if (button.getText().equalsIgnoreCase("Cosmetics")) {

            } else if (button.getText().equalsIgnoreCase("Back")) {
                new LaunchFrame();
            }

            frame.dispose();

        }

    }
}
