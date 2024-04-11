import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchFrame implements ActionListener {

    private final JButton button;
    private final JFrame frame;

    private JLabel head;
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

        head = new SnakeHead(250, 300, 10, 10);
        head.setVisible(true);
        frame.add(head);


        frame.setSize(500, 700);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        playIntroAnimation(); //FIXME

    }

    private void playIntroAnimation() {

        //250/ 4 = 62,5
        for (int i = 0; i < 10; i++) {
            SnakeTail tailpiece = new SnakeTail(head.getX(), head.getY(), 10, 10);
            frame.add(tailpiece);
            head.setLocation(head.getX(), head.getY() + 10);
        }
        frame.revalidate();
        frame.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            new GameFrame();
            frame.dispose();
        }
    }
}
