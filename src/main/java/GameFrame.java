import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class GameFrame extends JFrame {

    private JFrame frame;
    private ImageIcon image;
    private JPanel panel;

    public GameFrame() {
        frame = new JFrame();
        panel = new JPanel();

        initializeFrame();
    }

    private void initializeFrame() {
        try {
            image = new ImageIcon(Objects.requireNonNull(getClass().getResource("Snake.png")));
            frame.setIconImage(image.getImage());

        } catch (NullPointerException e) {
            System.out.println("Image not found");
        }
        frame.setTitle("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.addKeyListener(new KeyListener());

        frame.add(panel);
        frame.setVisible(true);
    }


}
