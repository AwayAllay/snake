import javax.swing.*;
import java.awt.*;

public class SnakeTail extends JLabel{
    public SnakeTail( int px, int py, int pwidht, int pheight){

        setBackground(new Settings().getSkin().tailColor);
        setOpaque(true);
        setBounds(px, py, pwidht, pheight);
        setVisible(true);

    }

}

