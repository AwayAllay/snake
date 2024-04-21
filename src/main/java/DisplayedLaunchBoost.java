import javax.swing.*;
import java.awt.*;

public class DisplayedLaunchBoost extends JLabel {

    public DisplayedLaunchBoost(int px, int py, int pwidth, int pheight, Color color){

        if (color == null) color = new Color(180, 0, 0);

        setBackground(color);
        setOpaque(true);
        setBounds(px, py, pwidth, pheight);
        setVisible(true);
    }

}
