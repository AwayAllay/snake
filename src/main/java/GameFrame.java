import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    JFrame frame;

    public GameFrame(){
        frame = new JFrame();
        frame.setTitle("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setSize(2000, 2000);
        frame.setLayout(null);
        frame.addKeyListener(new KeyListener());
        frame.setVisible(true);
    }


}
