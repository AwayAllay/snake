import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchFrame implements ActionListener {

    private final JButton button;
    private final JFrame frame;

    private ImageIcon image;
    public LaunchFrame(){

        frame = new JFrame("Launch game");

        try {
            image = new ImageIcon(getClass().getResource("Snake.png"));
            frame.setIconImage(image.getImage());

        }catch (NullPointerException e){
            System.out.println("Image not found");
        }

        JPanel panel = new JPanel(null);

        button = new JButton("Play game!");
        button.setBounds(125, 500,250, 60);
        button.setFocusable(false);
        button.addActionListener(this);

        panel.add(button);

        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            frame.dispose();
        }
    }
}
