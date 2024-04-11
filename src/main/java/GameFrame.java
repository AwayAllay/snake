import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    JFrame frame;
    ImageIcon image;

    public GameFrame(){
        frame = new JFrame();
        try {
            image = new ImageIcon(getClass().getResource("Snake.png"));
            frame.setIconImage(image.getImage());

        }catch (NullPointerException e){
            System.out.println("Image not found");
        }
        frame.setTitle("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.addKeyListener(new KeyListener());
        frame.setVisible(true);
    }


}
