import javax.swing.*;
import java.util.Objects;

public class DiedFrame {

    private final Settings settings;
    private final GameStuff gameStuff;

    private final JFrame frame;

    public DiedFrame(Settings settings, GameStuff gameStuff) {
        this.settings = settings;
        this.gameStuff = gameStuff;

        frame = new JFrame("You died");
        prepareFrame();
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

    }

}
