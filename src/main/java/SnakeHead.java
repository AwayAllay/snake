import javax.swing.*;
import java.awt.*;

public class SnakeHead extends JLabel {
    public SnakeHead(int px, int py, int pwidht, int pheight){

        setBackground(new Color(0, 102, 0));
        setOpaque(true);
        setBounds(px, py, pwidht, pheight);
        setVisible(true);

    }
}
