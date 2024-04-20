import javax.swing.*;

public class SnakeTail extends JLabel{
    public SnakeTail( int px, int py, int pwidht, int pheight){

        //setBackground(new Settings().getSkin().getTailColor());
        setOpaque(true);
        setBounds(px, py, pwidht, pheight);
        setVisible(true);

    }

}

