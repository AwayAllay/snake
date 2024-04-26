import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public abstract class GameFrame extends JFrame {

    //TODO give time for next level
    private final JFrame frame;
    private final JPanel panel;
    private final JPanel actionBar;
    private final JLabel level;
    private final JLabel tail1;
    private final JLabel tail2;
    private final JLabel tail3;
    private final JLabel head;
    private final KeyListener keyListener;
    private final PlaytimeManager playtimeManager;
    private final Settings settings;
    private final GameStuff gameStuff;
    public static final int FRAME_WIDTH_PX = 2096;
    public static final int FRAME_HEIGHT_PX = 1198;
    public static final int FIELD_WIDTH_PX = 20;
    public static final int FIELD_HEIGHT_PX = 20;

    /**FRAME_WIDTH_PX / FIELD_WIDTH_PX but with some fixes for the actual frame width*/
    public static final int NUM_FIELDS_HORIZ = 104;
    public static final int NUM_FIELDS_VERT = 55;

    public GameFrame(final Settings settings, final GameStuff gameStuff) {
        frame = new JFrame();

        this.settings = settings;
        this.gameStuff = gameStuff;


        panel = new JPanel();
        panel.setBackground(new Color(139, 90, 43));
        panel.setLayout(null);

        removeObstaclesFromPanel();

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
        
        //The displayed lives
        JLabel lives = new JLabel("lives: " + gameStuff.getLives());
        lives.setBounds(519, 5, 213, 50);
        lives.setBackground(Color.WHITE);
        lives.setOpaque(true);
        lives.setFont(new Font("Ariral", Font.BOLD, 50));

        //The displayed points
        JLabel points = new JLabel(String.valueOf(gameStuff.getPoints()));
        points.setBounds(1794, 5, 202, 50);
        points.setBackground(Color.WHITE);
        points.setOpaque(true);
        points.setFont(new Font("Ariral", Font.BOLD, 50));

        //The displayed amount of keys
        JLabel keys = new JLabel("keys: " + gameStuff.getKeyAmount() + "/5");
        keys.setBounds(1379, 5, 213, 50);
        keys.setBackground(Color.WHITE);
        keys.setOpaque(true);
        keys.setFont(new Font("Ariral", Font.BOLD, 50));
        
        //Timer-Label for the game-timer
        JLabel timer = new JLabel("00:00:00");
        timer.setBounds(100, 5, 202, 50);
        timer.setBackground(Color.WHITE);
        timer.setOpaque(true);
        timer.setFont(new Font("Ariral", Font.BOLD, 50));

        //SnakeTail
        tail1 = new SnakeTail(1040, 580, FIELD_WIDTH_PX, FIELD_HEIGHT_PX );
        tail1.setBackground(settings.getSkin().getTailColor());
        tail1.setOpaque(true);

        //SnakeTail2
        tail2 = new SnakeTail(1020, 580, FIELD_WIDTH_PX, FIELD_HEIGHT_PX );
        tail2.setBackground(settings.getSkin().getTailColor());
        tail2.setOpaque(true);

        //SnakeTail3
        tail3 = new SnakeTail(1000, 580, FIELD_WIDTH_PX, FIELD_HEIGHT_PX );
        tail3.setBackground(settings.getSkin().getTailColor());
        tail3.setOpaque(true);

        //SnakeHead
        head = new SnakeHead(1060 ,580, FIELD_WIDTH_PX, FIELD_HEIGHT_PX);
        head.setBackground(settings.getSkin().getHeadColor());
        head.setOpaque(true);

        //Adds the labels to the action-Bar panel
        actionBar.add(keys);
        actionBar.add(points);
        actionBar.add(lives);
        actionBar.add(level);
        actionBar.add(timer);

        boolean[][] obstacles = createObstacles();

        playtimeManager = new PlaytimeManager(gameStuff, timer);

        keyListener = new KeyListener(head, panel, settings, frame, gameStuff, points, lives, keys, playtimeManager, obstacles);
        keyListener.addTail((SnakeTail) tail1);
        keyListener.addTail((SnakeTail) tail2);
        keyListener.addTail((SnakeTail) tail3);

        initializeFrame();
    }

    private boolean[][] createObstacles() {

        boolean [][] result = null;

        switch (gameStuff.getCurrentLevel()){

            case LEVEL1 -> {
                System.out.println("level1");
                result = translateLevel("level1.txt");}
            case LEVEL2 -> {
                System.out.println("level2");
                result = translateLevel("level2.txt");}
            case LEVEL3 -> {
                System.out.println("level 3");
                result = translateLevel("level3.txt");}

        }
        //TODO IMPORTANT FOR NEW LEVELS!

        return result;
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
        frame.setSize(FRAME_WIDTH_PX, FRAME_HEIGHT_PX);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.addKeyListener(keyListener);

        actionBar.setBounds(0, 0, frame.getWidth(), 60);

        panel.add(actionBar, BorderLayout.NORTH);
        panel.add(tail1);
        panel.add(tail2);
        panel.add(tail3);
        panel.add(head);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void setLevel(String newLevel){
        level.setText(newLevel);
    }

    public boolean[][] translateLevel(final String fileName){
        //105 Zeichen in X Richtung
        //57 in Y Richtung

        boolean[][] result = new boolean[NUM_FIELDS_HORIZ][NUM_FIELDS_VERT];

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
                label.setBackground(new Color(205, 133, 63));
                label.setOpaque(true);

                if (c == ' '){
                    x += FIELD_WIDTH_PX;
                }else if (c == 'X'){
                    label.setBounds(x, y, FIELD_WIDTH_PX, FIELD_HEIGHT_PX);
                    panel.add(label);
                    result[x /  FIELD_WIDTH_PX][(y - 60) / FIELD_HEIGHT_PX] = true;
                    x += FIELD_WIDTH_PX;
                }
            }
            x = 0;
            y += FIELD_HEIGHT_PX;
        }
        return result;
    }

    private void removeObstaclesFromPanel(){
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel){
                panel.remove(component);
            }
        }
    }

    public void startTimer(){
       playtimeManager.startTimer();
    }

}
