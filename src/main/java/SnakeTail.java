import javax.swing.*;

public class SnakeTail extends JLabel{
    public SnakeTail( int px, int py, int pwidht, int pheight){
        setOpaque(true);
        setBounds(px, py, pwidht, pheight);
        setVisible(true);
    }
}

