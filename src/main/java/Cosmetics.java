import javax.swing.*;
import java.util.Objects;

public class Cosmetics {

    private final JFrame frame;
    private ImageIcon image;

    public Cosmetics() {

        frame = new JFrame();
        prepareLaunchFrame();

    }

    /**
     * Sets up the frame
     */
    private void prepareLaunchFrame() {

        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            image = new ImageIcon(Objects.requireNonNull(getClass().getResource("Snake.png")));
            frame.setIconImage(image.getImage());
        } catch (NullPointerException e) {
            System.out.println("Image not found");
        }
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

}
