import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Skins implements ActionListener {

    private final JFrame frame;

    private final List<SnakeTail> tails = new ArrayList<>();

    public Skins() {

        frame = new JFrame("Skins");
        JPanel panel = new JPanel(null);
        prepareLaunchFrame();

        JButton next = new JButton("Next");
        next.setFocusable(false);
        next.setBounds(375, 160, 60, 160);
        next.addActionListener(this);
        panel.add(next);

        JButton last = new JButton("Last");
        last.setFocusable(false);
        last.setBounds(65, 160, 60, 160);
        last.addActionListener(this);
        panel.add(last);

        JButton select = new JButton("Select skin");
        select.setFocusable(false);
        select.setBounds(125, 400, 250, 60);
        select.addActionListener(this);
        panel.add(select);

        JButton backToLaunch = new JButton("Back");
        backToLaunch.setFocusable(false);
        backToLaunch.setBounds(125, 500, 250, 60);
        backToLaunch.addActionListener(this);
        panel.add(backToLaunch);

        JLabel label = new JLabel("Choose your skin to play!");
        label.setBounds(130, 60, 250, 20);
        label.setFont(new Font("Ariral", Font.ITALIC, 20));
        panel.add(label);

        for (int i = 190; i < 281; i+=20) {
            SnakeTail tail = new SnakeTail(i, 280, 20, 20);
            tails.add(tail);
            panel.add(tail);
        }

        SnakeHead head = new SnakeHead(290, 280, 20, 20);
        panel.add(head);

        frame.add(panel);

    }

    /**Sets up the frame*/
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

        if (e.getSource() instanceof JButton button){

            if (button.getText().equalsIgnoreCase("Next")){
                frame.dispose();

            } else if (button.getText().equalsIgnoreCase("Last")) {
                frame.dispose();

            } else if (button.getText().equalsIgnoreCase("Select skin")) {
                frame.dispose();

            }else {
                frame.dispose();
                new SettingsFrame();
            }


        }
    }
}
