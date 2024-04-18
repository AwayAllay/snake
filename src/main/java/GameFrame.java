import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public abstract class GameFrame extends JFrame {

    private final JFrame frame;
    private final JPanel panel;

    private final JPanel actionBar;

    private final JLabel level;
    
    private final JLabel timer;

    private final Timer setTimeTimer;

    private final JLabel tail;

    private final List<SnakeTail> tails = new KeyListener().getSnakeTails();

    public GameFrame() {
        frame = new JFrame();

        tails.clear();

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        //The bar above the game field
        actionBar = new JPanel();
        actionBar.setBackground((new Color(96, 96,96)));
        actionBar.setPreferredSize(new Dimension(frame.getWidth(), 60));
        actionBar.setLayout(null);

        //The displayed level
        level = new JLabel("LEVEL 0");
        level.setBounds(949, 5, 203, 50);
        level.setBackground(Color.WHITE);
        level.setOpaque(true);
        level.setFont(new Font("Ariral", Font.BOLD, 50));

        //Timer-Label for the game-timer
        timer = new JLabel("00:00:00");
        timer.setBounds(100, 5, 202, 50);
        timer.setBackground(Color.WHITE);
        timer.setOpaque(true);
        timer.setFont(new Font("Ariral", Font.BOLD, 50));

        //
        tail = new SnakeTail(1040, 595, 10, 10);
        tail.setBounds(1040,595,10,10);
        tail.setBackground(new Settings().getSkin().tailColor);
        tail.setOpaque(true);
        tails.add((SnakeTail) tail);

        actionBar.add(level);
        actionBar.add(timer);

        initializeFrame();

        setTimeTimer = new Timer();
    }

    private void initializeFrame() {
        try {
            ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("Snake.png")));
            frame.setIconImage(image.getImage());

        } catch (NullPointerException e) {
            System.out.println("Image not found");
        }
        frame.setTitle("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setSize(2100, 1200);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.addKeyListener(new KeyListener());

        actionBar.setBounds(0, 0, frame.getWidth(), 60);

        panel.add(actionBar, BorderLayout.NORTH);
        panel.add(tail);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void setLevel(String newLevel){
        level.setText(newLevel);
    }
    private void setTime(int timeToSet) {

        String string = "";

        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        if (timeToSet / 60 / 60 >= 1){
            hours = timeToSet / 60 / 60;
            timeToSet = timeToSet - ((timeToSet / 60/ 60) * 60 * 60);
        }
        if (timeToSet / 60  >= 1){
            minutes = timeToSet / 60;
            timeToSet = timeToSet - ((timeToSet / 60) * 60);
        }
        if (timeToSet >= 1){
            seconds=timeToSet;
        }
        if (hours<=9){
            string = string + "0" + hours + ":";
        }else {
            string = string + hours + ":";
        }
        if (minutes<=9){
            string = string + "0" + minutes + ":";
        }else {
            string = string + minutes + ":";
        }
        if (seconds<=9){
            string = string + "0" + seconds;
        }else {
            string = string + seconds;
        }

        timer.setText(string);


    }

    public void startTimer(final int pTime){
       setTimeTimer.schedule(new TimerTask() {

           int time = pTime;
           @Override
           public void run() {
               setTime(time);
               time++;
           }
       }, 0, 1000);
    }

    public JPanel getPanel(){
        return panel;
    }

}
