import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Boost extends JLabel {

    public Boost(int px, int py, int pwidth, int pheight, Color color){

        if (color == null) color = new Color(180, 0, 0);

        /*try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("Boost.png")));
            Image scaledIcon = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            ImageIcon finalIcon = new ImageIcon(scaledIcon);
            setIcon(finalIcon);
        }catch (NullPointerException e){
            System.out.println("Image not found");
        }*/

        setBackground(color);
        setOpaque(true);
        setBounds(px, py, pwidth, pheight);
        setVisible(true);
    }

}
