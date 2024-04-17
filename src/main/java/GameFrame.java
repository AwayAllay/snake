import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public abstract class GameFrame extends JFrame {

    private final JFrame frame;
    private final JPanel panel;

    private final JPanel actionBar;

    private final JLabel level;
    
    private final JLabel timer;

    public GameFrame() {
        frame = new JFrame();

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        actionBar = new JPanel();
        actionBar.setBackground((new Color(96, 96,96)));
        actionBar.setPreferredSize(new Dimension(frame.getWidth(), 60));
        actionBar.setLayout(null);

        level = new JLabel("LEVEL 0");
        level.setBounds(949, 5, 203, 50);
        level.setBackground(Color.WHITE);
        level.setOpaque(true);
        level.setFont(new Font("Ariral", Font.BOLD, 50));
        
        timer = new JLabel();
        timer.setBounds(100, 5, 202, 50);
        timer.setBackground(Color.WHITE);
        timer.setOpaque(true);
        timer.setFont(new Font("Ariral", Font.BOLD, 50));

        actionBar.add(level);
        actionBar.add(timer);

        initializeFrame();
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

        panel.add(actionBar, BorderLayout.NORTH);
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

        Timer setTimeTimer = new Timer();
        setTimeTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setTime(pTime + 1);
            }
        }, 0, 1000);
    }

    public JPanel getPanel(){
        return panel;
    }

}
