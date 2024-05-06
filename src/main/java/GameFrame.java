import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.List;
import java.util.Timer;
import java.util.*;

public class GameFrame extends JFrame {
    private final JFrame frame;
    private final JPanel panel;
    private final JPanel actionBar;
    private final JLabel keys;
    private final JLabel level;
    private final JLabel tail1;
    private final JLabel tail2;
    private final JLabel tail3;
    private final JLabel head;
    private final Settings settings;
    private final KeyListener keyListener;
    private final PlaytimeManager playtimeManager;
    private final GameStuff gameStuff;
    private Levels currentLevel;
    public static final int FRAME_WIDTH_PX = 2096;
    public static final int FRAME_HEIGHT_PX = 1198;
    public static final int FIELD_WIDTH_PX = 20;
    public static final int FIELD_HEIGHT_PX = 20;

    /**FRAME_WIDTH_PX / FIELD_WIDTH_PX but with some fixes for the actual frame width*/
    public static final int NUM_FIELDS_HORIZ = 104;
    public static final int NUM_FIELDS_VERT = 55;

    public GameFrame(final Settings settings, final GameStuff gameStuff) {
        frame = new JFrame();

        this.gameStuff = gameStuff;
        this.settings = settings;
        currentLevel = gameStuff.getCurrentLevel();

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
        level = new JLabel("LEVEL 10", SwingConstants.CENTER);
        level.setBounds(949, 5, 203, 50);
        level.setBackground(Color.WHITE);
        level.setOpaque(true);
        level.setFont(new Font("Ariral", Font.BOLD, 35));
        
        //The displayed lives
        JLabel lives = new JLabel("lives: " + gameStuff.getLives(), SwingConstants.CENTER);
        lives.setBounds(519, 5, 213, 50);
        lives.setBackground(Color.WHITE);
        lives.setOpaque(true);
        lives.setFont(new Font("Ariral", Font.BOLD, 35));

        //The displayed points
        JLabel points = new JLabel(String.valueOf(gameStuff.getPoints()), SwingConstants.CENTER);
        points.setBounds(1794, 5, 202, 50);
        points.setBackground(Color.WHITE);
        points.setOpaque(true);
        points.setFont(new Font("Ariral", Font.BOLD, 35));

        //The displayed amount of keys
        keys = new JLabel("keys: " + gameStuff.getKeyAmount() + "/1", SwingConstants.CENTER);
        keys.setBounds(1379, 5, 213, 50);
        keys.setBackground(Color.WHITE);
        keys.setOpaque(true);
        keys.setFont(new Font("Ariral", Font.BOLD, 35));
        
        //Timer-Label for the game-timer
        JLabel timer = new JLabel("00:00:00", SwingConstants.CENTER);
        timer.setBounds(100, 5, 202, 50);
        timer.setBackground(Color.WHITE);
        timer.setOpaque(true);
        timer.setFont(new Font("Ariral", Font.BOLD, 35));

        //SnakeTail
        tail1 = new SnakeTail(1040, 600, FIELD_WIDTH_PX, FIELD_HEIGHT_PX );
        tail1.setBackground(settings.getSkin().getTailColor());
        tail1.setOpaque(true);

        //SnakeTail2
        tail2 = new SnakeTail(1020, 600, FIELD_WIDTH_PX, FIELD_HEIGHT_PX );
        tail2.setBackground(settings.getSkin().getTailColor());
        tail2.setOpaque(true);

        //SnakeTail3
        tail3 = new SnakeTail(1000, 600, FIELD_WIDTH_PX, FIELD_HEIGHT_PX );
        tail3.setBackground(settings.getSkin().getTailColor());
        tail3.setOpaque(true);

        //SnakeHead
        head = new SnakeHead(1060 ,600, FIELD_WIDTH_PX, FIELD_HEIGHT_PX);
        head.setBackground(settings.getSkin().getHeadColor());
        head.setOpaque(true);

        //Adds the labels to the action-Bar panel
        actionBar.add(keys);
        actionBar.add(points);
        actionBar.add(lives);
        actionBar.add(level);
        actionBar.add(timer);

        boolean[][] obstacles = createObstacles();
        gameStuff.setObstacles(obstacles);

        playtimeManager = new PlaytimeManager(gameStuff, timer);

        keyListener = new KeyListener(head, panel, settings, frame, gameStuff, points, lives, keys, playtimeManager);
        keyListener.addTail((SnakeTail) tail1);
        keyListener.addTail((SnakeTail) tail2);
        keyListener.addTail((SnakeTail) tail3);

        initializeFrame();

        setLevel("LEVEL " + gameStuff.getCurrentLevel().getLevelNumber());
        startTimer();
        checkForNewLevel();
    }

    /**Checks if the current level has changed and if so it calls the nextLevel method*/
    private void checkForNewLevel() {
        Timer checkingForNewLevel = new Timer();
        checkingForNewLevel.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!gameStuff.getCurrentLevel().equals(currentLevel)){
                    nextLevel();
                }
            }
        }, 0, 1000);
    }

    private void nextLevel() {
        currentLevel = gameStuff.getCurrentLevel();
        keyListener.respawn();

        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel && !component.getLocation().equals(head.getLocation())
            && doesEqualTail(component))
                panel.remove(component);
        }

        boolean[][] obstacles = createObstacles();
        gameStuff.setObstacles(obstacles);
        keyListener.update();

        setLevel("LEVEL " + gameStuff.getCurrentLevel().getLevelNumber());
        keys.setText("keys: " + gameStuff.getKeyAmount() + "/1");

        keyListener.pauseTimer();

        frame.revalidate();
        frame.repaint();

        keyListener.startMoving = true;

        /*Looks if the player could have unlocked a new skin by getting the boolean from the gameStuff class which is set
         * by the keyListener. If its true it calls the sendUnlockThing method in the keyListener class*/
        if (gameStuff.isSendUnlockMessage()){
            keyListener.sendUnlockThing(settings.getUnlockedLevel());
        }

        checkForAchievement();
    }

    private void checkForAchievement() {
        //Looks for PARENT achievement
        if (gameStuff.getCurrentLevel().equals(Levels.LEVEL20) && !settings.isPARENTcollected()
        && settings.getMode().equals(Modes.ADULT)){
            
            new Popup(Achievement.PARENT.getName(), panel, KeyListener.ACHIEVEMENT_TEXT_COLOR, KeyListener.ACHIEVEMENT_BACKGROUND_COLOR,
                    KeyListener.ACHIEVEMENT_BORDER_COLOR, KeyListener.ACHIEVEMENT_DISPLAYTIME, gameStuff, 
                    Achievement.PARENT.getDescription());
            settings.setPARENTcollected(true);
            new SettingsManager().save(settings);
        }
        //Looks for SERPENT_GOD achievement
        if (gameStuff.getCurrentLevel().equals(Levels.LEVEL20) && !settings.isSERPENT_GODcollected()
                && settings.getMode().equals(Modes.GOD)){

            new Popup(Achievement.SERPENT_GOD.getName(), panel, KeyListener.ACHIEVEMENT_TEXT_COLOR, KeyListener.ACHIEVEMENT_BACKGROUND_COLOR,
                    KeyListener.ACHIEVEMENT_BORDER_COLOR, KeyListener.ACHIEVEMENT_DISPLAYTIME, gameStuff,
                    Achievement.SERPENT_GOD.getDescription());
            settings.setSERPENT_GODcollected(true);
            new SettingsManager().save(settings);
        }
        //Looks for GETTING_BETTER achievement
        if (gameStuff.getCurrentLevel().equals(Levels.LEVEL20) && !settings.isGETTING_BETTERcollected()
                && settings.getMode().equals(Modes.BEGINNER)){

            new Popup(Achievement.GETTING_BETTER.getName(), panel, KeyListener.ACHIEVEMENT_TEXT_COLOR, KeyListener.ACHIEVEMENT_BACKGROUND_COLOR,
                    KeyListener.ACHIEVEMENT_BORDER_COLOR, KeyListener.ACHIEVEMENT_DISPLAYTIME, gameStuff,
                    Achievement.GETTING_BETTER.getDescription());
            settings.setGETTING_BETTERcollected(true);
            new SettingsManager().save(settings);
        }
        //Looks for DIVINITY achievement
        if (gameStuff.getCurrentLevel().equals(Levels.LEVEL60) && !settings.isDIVINITYcollected()
                && settings.getMode().equals(Modes.BEGINNER)){

            new Popup(Achievement.DIVINITY.getName(), panel, KeyListener.ACHIEVEMENT_TEXT_COLOR, KeyListener.ACHIEVEMENT_BACKGROUND_COLOR,
                    KeyListener.ACHIEVEMENT_BORDER_COLOR, KeyListener.ACHIEVEMENT_DISPLAYTIME, gameStuff,
                    Achievement.DIVINITY.getDescription());
            settings.setDIVINITYcollected(true);
            new SettingsManager().save(settings);
        }
    }


    private boolean doesEqualTail(Component component) {

        for (SnakeTail tail : keyListener.getTails()) {
            if (tail.getLocation().equals(component.getLocation())){
                return false;
            }
        }
        return true;
    }

    private boolean[][] createObstacles() {

        boolean [][] result = null;

        switch (gameStuff.getCurrentLevel()){

            case LEVEL1 -> result = translateLevel("level1.txt");
            case LEVEL2 -> result = translateLevel("level2.txt");
            case LEVEL3 -> result = translateLevel("level3.txt");
            case LEVEL4 -> result = translateLevel("level4.txt");
            case LEVEL5 -> result = translateLevel("level5.txt");
            case LEVEL6 -> result = translateLevel("level6.txt");
            case LEVEL7 -> result = translateLevel("level7.txt");
            case LEVEL8 -> result = translateLevel("level8.txt");
            case LEVEL9 -> result = translateLevel("level9.txt");
            case LEVEL10 -> result = translateLevel("level10.txt");
            case LEVEL11 -> result = translateLevel("level11.txt");
            case LEVEL12 -> result = translateLevel("level12.txt");

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

    private boolean[][] translateLevel(final String fileName){
        //105 Zeichen in X Richtung
        //57 in Y Richtung

        boolean[][] result = new boolean[NUM_FIELDS_HORIZ][NUM_FIELDS_VERT];

        InputStream inputStream = getClass().getResourceAsStream(fileName);
        Scanner scanner = null;
        int x = 0;
        int y = 60;

        try {
            scanner = new Scanner(Objects.requireNonNull(inputStream));
        } catch (Exception e) {
            System.out.println("File not found");
        }

        List<String> lines = new ArrayList<>();
        List<char[]> lineChars = new ArrayList<>();


        while (Objects.requireNonNull(scanner).hasNext()) {
            lines.add(scanner.nextLine());
        }

        for (String line : lines) {
            lineChars.add(line.toCharArray());
        }

        for (char[] charArray : lineChars) {
            for (char c : charArray) {

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
