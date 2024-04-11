import javax.swing.*;
import java.awt.*;

public class SnakeTail extends JLabel{
    public SnakeTail( int px, int py, int pwidht, int pheight){

        setBackground(new Color(0, 204, 0));
        setOpaque(true);
        setBounds(px, py, pwidht, pheight);
        setVisible(true);

    }

}

