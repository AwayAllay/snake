import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class LaunchFrame implements ActionListener {

    private final JButton button;
    private final JFrame frame;

    private Timer timer;

    private JLabel head;
    private JLabel letter;
    private ImageIcon image;
    private JPanel panel;
    public LaunchFrame(){

        frame = new JFrame("Launch game");
        timer = new Timer();

        letter = new JLabel("SNAKE");
        letter.setBounds(145, 100, 250, 60);
        letter.setVisible(true);
        letter.setFont(new Font("Arial", Font.BOLD, 60));

        try {
            image = new ImageIcon(getClass().getResource("Snake.png"));
            frame.setIconImage(image.getImage());

        }catch (NullPointerException e){
            System.out.println("Image not found");
        }

        panel = new JPanel(null);
        button = new JButton("Play game!");
        button.setBounds(125, 500,250, 60);
        button.setFocusable(false);
        button.addActionListener(this);
        panel.add(button);
        panel.add(letter);

        head = new SnakeHead(125, -10, 10, 10);
        head.setVisible(true);
        frame.add(head);


        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        playIntroAnimation();

    }

    private void playIntroAnimation() {

        //250/ 4 = 62,5
        drawText();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SnakeTail tailpiece = new SnakeTail(head.getX(), head.getY(), 10, 10);
                panel.add(tailpiece);
                runDown();
            }
        }, 3500, 200);
    }

    private void drawText() { //FIXME
        String word = "SNAKE";
        int wordWidht = word.length() * 50;
        int xOffset = (frame.getWidth() - wordWidht) / 2;

        for (int i = 0; i < word.length(); i++) {
            Timer timer1 = new Timer();
            timer1.scheduleAtFixedRate(new TimerTask() {

                int i = 0;
                @Override
                public void run() {
                    if (i < word.length()) {
                        i++;
                        JLabel letter = new JLabel(String.valueOf(word.charAt(i)));
                        letter.setFont(new Font("Arial", Font.BOLD, 24));
                        letter.setBounds(xOffset + i * 50, 200, 50, 50); // Positionierung der Buchstaben
                        frame.add(letter);
                    }else {
                        cancel();
                    }
                }
            }, 3000, 200);
            //JLabel letter = new JLabel(String.valueOf(word.charAt(i)));
            //letter.setFont(new Font("Arial", Font.BOLD, 24));
            //letter.setBounds(xOffset + i * 50, 200, 50, 50); // Positionierung der Buchstaben
            //frame.add(letter);
        }
    }

    private void runUp(){
        head.setLocation(head.getX(), head.getY() - 10);
    }
    private void runLeft(){
        head.setLocation(head.getX() - 10, head.getY());
    }
    private void runDown(){
        head.setLocation(head.getX(), head.getY() + 10);
    }
    private void runRight(){
        head.setLocation(head.getX() + 10, head.getY());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            new GameFrame();
            frame.dispose();
        }
    }
}
