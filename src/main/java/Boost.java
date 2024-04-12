import javax.swing.*;
import java.awt.*;

public class Boost extends JLabel {

    public Boost(int px, int py, int pwidht, int pheight, Color color){

        if (color == null) color = new Color(180, 0, 0);

        setBackground(color);
        setOpaque(true);
        setBounds(px, py, pwidht, pheight);
        setVisible(true);
    }

    public void setColor(Color pcolor){
        setBackground(pcolor);
    }

}
