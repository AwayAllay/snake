import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.Timer;

public abstract class GameFrame extends JFrame {

    private final JFrame frame;
    private final JPanel panel;

    private final JPanel actionBar;

    private final JLabel level;
    
    private final JLabel timer;

    private final Timer setTimeTimer;

    private final JLabel tail;
    
    private final JLabel head;

    private final Settings settings;

    private final List<SnakeTail> tails = new LinkedList<>();

    public GameFrame(final Settings settings) {
        frame = new JFrame();

        this.settings = settings; //TODO get the fucking settings from any Clas!!!!!!!!!!!!

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

        //SnakeTail
        tail = new SnakeTail(1030, 590, 20, 20);
        tail.setBackground(settings.getSkin().tailColor);
        tail.setOpaque(true);
        tails.add((SnakeTail) tail);

        //SnakeHead
        head = new SnakeHead(1050 ,590, 20, 20);
        head.setBackground(settings.getSkin().headColor);
        head.setOpaque(true);

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
        frame.setSize(2096, 1198);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.addKeyListener(new KeyListener(head, panel, settings));

        actionBar.setBounds(0, 0, frame.getWidth(), 60);

        panel.add(actionBar, BorderLayout.NORTH);
        panel.add(tail);
        panel.add(head);

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
    public void translateLevel(final String fileName){
        //105 Zeichen in X Richtung
        //57 in Y Richtung
        File file = new File(fileName);
        Scanner scanner = null;
        int x = 0;
        int y = 60;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        List<String> lines = new ArrayList<>();
        List<char[]> lineChars = new ArrayList<>();


        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        for (String line : lines) {
            lineChars.add(line.toCharArray());
        }

        for (char[] charArry : lineChars) {
            for (char c : charArry) {

                JLabel label = new JLabel();
                label.setBackground(Color.ORANGE);
                label.setOpaque(true);

                if (c == '0'){
                    x += 20;
                }else if (c == 'X'){
                    label.setBounds(x, y, 20, 20);
                    panel.add(label);
                    x+= 20;
                }

            }
            x = 0;
            y +=20;
        }
    }
    public void startTimer(final int pTime){
       setTimeTimer.schedule(new TimerTask() {
           private int time = pTime;
           @Override
           public void run() {
               setTime(time);
               time++;
           }
       }, 0, 1000);
    }

}
