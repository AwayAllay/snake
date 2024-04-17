import javax.swing.*;
import java.util.Objects;

public class GameRuleFrame {

    private final JFrame frame;

    public GameRuleFrame(){

        //500x700
        frame = new JFrame("Game-rules");

        JPanel panel = new JPanel();

        //TODO

        prepareLaunchFrame();
        frame.add(panel);

    }

    /**Prepares the main frame*/
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



}
