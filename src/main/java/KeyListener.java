import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class KeyListener implements java.awt.event.KeyListener {

    /**
     * RIGHT LEFT UP AND DOWN (directions in which snake is moving)
     */
    private MovingDirections direction = MovingDirections.RIGHT;
    /**
     * The timer which causes the snake to move frequently
     */
    private Timer timer;
    /**
     * true if the snake is currently entering a new level
     */
    private boolean enteringNewLevel = false;
    /**
     * says if the snake can die or not
     */
    private boolean isInvinceble = false;
    /**
     * The head label of the snake
     */
    private final JLabel head;
    /**
     * The label of the displayed boost
     */
    private final JLabel boost;
    /**
     * says if all the keys are collected and potentially the door could open
     */
    private boolean allKeysCollected;
    /**
     * The main frame of the game
     */
    private final JFrame gameFrame;
    /**
     * The main panel (The panel on which the snake and the obstacles are)
     */
    private final JPanel panel;
    /**
     * The label on which the current points are displayed
     */
    private final JLabel points;
    /**
     * The label which displays the current lives of the snake
     */
    private final JLabel lives;
    /**
     * The label which displays the current amount of collected keys
     */
    private final JLabel keys;
    /**
     * The settings object which is given every class though the constructor
     */
    private final Settings settings;
    /**
     * The gameStuff stuff which is given almost every class through the constructor
     */
    private final GameStuff gameStuff;
    /**
     * The instance of the class which manages the displayed timer
     */
    private final PlaytimeManager playtimeManager;
    /**
     * The list of the tails of the snake
     */
    private final List<SnakeTail> tails = new LinkedList<>();
    /**
     * The list of pressed directions in which the first object will be taken for the direction whenever the
     * snake is moving
     */
    private final List<MovingDirections> pressedDirections = new LinkedList<>();
    /**
     * Says if the snake has already started moving so pressing the space-bar again don´t makes it faster
     */
    public boolean startMoving = true;
    /**
     * Says if the timer moving the snake has started so it cant be started again
     */
    private boolean timerStartedOnce;
    /**
     * The type of the current boost
     */
    private IngameBoost currentBoost;

    /**
     * Constructor
     */
    public KeyListener(JLabel head, JPanel panel, Settings settings, JFrame gameFrame, GameStuff gameStuff, JLabel points, JLabel lives, JLabel keys, PlaytimeManager playtimeManager) {
        this.gameStuff = gameStuff;
        this.settings = settings;
        this.gameFrame = gameFrame;
        this.head = head;
        this.panel = panel;
        this.points = points;
        this.lives = lives;
        this.keys = keys;
        this.playtimeManager = playtimeManager;
        boost = new JLabel();
        boost.setBounds(600, 1000, GameFrame.FIELD_WIDTH_PX, GameFrame.FIELD_HEIGHT_PX);
        pressedDirections.add(MovingDirections.RIGHT);

        update();
    }

    public void update() {
        timerStartedOnce = false;
        currentBoost = IngameBoost.REGULAR_BOOST;
        spawnBoost();
        allKeysCollected = false;
        isInvinceble = false;
        System.out.println("Updated");
    }

    /**
     * Increases the snakes length by the given int in SnakeTails.
     * Does this by adding a tail to the position of the last snake in the tails list.
     */
    private void increaseSnakeLength(int timesToIncrease) {

        for (int i = 0; i < timesToIncrease; i++) {

            SnakeTail tailToAdd = new SnakeTail(tails.get(tails.size() - 1).getX(), tails.get(tails.size() - 1).getY(), GameFrame.FIELD_WIDTH_PX, GameFrame.FIELD_HEIGHT_PX);
            tailToAdd.setBackground(settings.getSkin().getTailColor());
            tailToAdd.setOpaque(true);
            panel.add(tailToAdd);
            tails.add(tailToAdd);
        }
    }

    /**
     * Keys:
     * W 87;
     * A 65;
     * S 83;
     * D 68;
     * Leertaste 32;
     * Esc 27;
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Implemented method which looks what key was pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            //W and arrow key up
            case 87, 38 -> {
                if (pressedDirections.get(pressedDirections.size() - 1) != MovingDirections.DOWN && direction != MovingDirections.UP) {
                    direction = MovingDirections.UP;
                    if (pressedDirections.size() < 5)
                        pressedDirections.add(direction);
                }
            }
            //A and arrow key left
            case 65, 37 -> {
                if (pressedDirections.get(pressedDirections.size() - 1) != MovingDirections.RIGHT && direction != MovingDirections.LEFT) {
                    direction = MovingDirections.LEFT;
                    if (pressedDirections.size() < 5)
                        pressedDirections.add(direction);
                }
            }

            //S and arrow key down
            case 83, 40 -> {
                if (pressedDirections.get(pressedDirections.size() - 1) != MovingDirections.UP && direction != MovingDirections.DOWN) {
                    direction = MovingDirections.DOWN;
                    if (pressedDirections.size() < 5)
                        pressedDirections.add(direction);
                }
            }
            //D and arrow key right
            case 68, 39 -> {
                if (pressedDirections.get(pressedDirections.size() - 1) != MovingDirections.LEFT && direction != MovingDirections.RIGHT) {
                    direction = MovingDirections.RIGHT;
                    if (pressedDirections.size() < 5)
                        pressedDirections.add(direction);
                }
            }
            //Esc
            case 27 -> openPauseMenu();

            //Space
            case 32 -> {
                if (startMoving) {
                    moveSnake();
                    startMoving = false;
                }
            }
            //B
            case 66 -> spawnBoost();
        }
    }

    /**
     * This starts the timer which will depending on the mode call moveAction() from 110-200 milliseconds
     */
    private void moveSnake() {
        timer = new Timer();
        timerStartedOnce = true;

        int speed = settings.getMode().getSpeed();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveAction();
            }
        }, 0, speed);
    }

    private void spawnBoost() {

        int randomNumber = new Random().nextInt(100) + 1;

        /*Set current boost depending on the randomNumber*/
        if (randomNumber <= 25) { //5!!
            currentBoost = IngameBoost.KEY_BOOST;
        } else if (randomNumber <= 45) {
            currentBoost = IngameBoost.REGULAR_BOOST;
        } else if (randomNumber <= 70) {
            currentBoost = IngameBoost.NICE_BOOST;
        } else if (randomNumber <= 73) {
            currentBoost = IngameBoost.MYTHICAL_BOOST;
        } else if (randomNumber <= 80) {
            currentBoost = IngameBoost.BAD_BOOST;
        } else if (randomNumber <= 85) {
            currentBoost = IngameBoost.HEALTH_BOOST;
        } else if (randomNumber <= 95) {
            currentBoost = IngameBoost.GOOD_BOOST;
        } else if (randomNumber == 96) {
            currentBoost = IngameBoost.GOD_BOOST;
        } else {
            currentBoost = IngameBoost.LOSER_BOOST;
        }

        spawnLabel();

    }

    private void spawnLabel() {

        boost.setBackground(currentBoost.getBoostColor());
        boost.setOpaque(true);
        //boost.setIcon(getBoostIcon());
        boost.setVisible(true);
        panel.add(boost);
        setRandomBoostLocation();

    }

    /*private Icon getBoostIcon() {

        ImageIcon image = null;
        String directory = "";

        switch (currentBoost){
            case HEALTH_BOOST -> directory = "Heart.png";
            case GOOD_BOOST -> directory = "Good.png";
        }


        try {
            image = new ImageIcon(Objects.requireNonNull(getClass().getResource(directory)));
        } catch (NullPointerException e) {
            System.out.println("Image not found");
        }
        return image;
    }*/

    /**
     * Sets a radom Location for the boost and looks if it would be on the snake or on one of
     * the obstacles. If so it will create a new Location for the boost by recursion.
     */
    private void setRandomBoostLocation() {
        int randomX = new Random().nextInt(104);
        int randomY = new Random().nextInt(55);

        System.out.println(gameStuff.getObstacles()[randomX][randomY]);

        //FIXME
        if (gameStuff.getObstacles()[randomX][randomY] || boostOnSnake(randomX, randomY)) {
            setRandomBoostLocation();
        } else {
            boost.setLocation(randomX * GameFrame.FIELD_WIDTH_PX, (randomY + 3) * GameFrame.FIELD_HEIGHT_PX);
            System.out.println(randomX + " " + randomY);
            boost.setVisible(true);
            panel.revalidate();
            panel.repaint();
        }

    }

    /**
     * Tests if the boost would spawn on the snake.
     * If so it returns true, otherwise false.
     */
    private boolean boostOnSnake(final int x, final int y) {

        for (SnakeTail tail : tails) {
            if (x == tail.getX() || y == tail.getY()) {
                return true;
            }
        }
        return false;
    }

    private void eatBoost() {

        gameStuff.setPoints(gameStuff.getPoints() + (long) currentBoost.getPoints() * settings.getMode().getModeMultiplier());
        gameStuff.setLives(gameStuff.getLives() + currentBoost.getHealthBoost());
        increaseSnakeLength(currentBoost.getLengthBoost());
        gameStuff.setKeyAmount(gameStuff.getKeyAmount() + currentBoost.getKeyBoost());

        points.setText(String.valueOf(gameStuff.getPoints() * settings.getMode().getModeMultiplier()));
        lives.setText("lives: " + gameStuff.getLives());
        keys.setText("keys: " + gameStuff.getKeyAmount() + "/1");

        panel.remove(boost);
        panel.revalidate();
        panel.repaint();

        if (gameStuff.getKeyAmount() >= 1) {
            allKeysCollected = true;
        }

        if (!allKeysCollected) {
            spawnBoost();
        } else {
            openDoor();
        }
    }

    private void openDoor() {
        //28, 103
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel &&
                    component.getBounds().contains(new Point(103 * GameFrame.FIELD_WIDTH_PX, (27 + 3) * GameFrame.FIELD_HEIGHT_PX))) {

                panel.remove(component);
                panel.revalidate();
                panel.repaint();
            }
        }
        new AchievementSlideThing(settings, gameStuff, "DOOR OPENED", panel,
                new Color(255, 255, 0), new Color(96, 96,96), new Color(255, 255, 0)).act();
    }

    private void moveAction() {

        moveTails();

        switch (pressedDirections.get(0)) {
            case UP -> head.setLocation(head.getX(), head.getY() - GameFrame.FIELD_HEIGHT_PX);
            case RIGHT -> head.setLocation(head.getX() + GameFrame.FIELD_WIDTH_PX, head.getY());
            case DOWN -> head.setLocation(head.getX(), head.getY() + GameFrame.FIELD_HEIGHT_PX);
            case LEFT -> head.setLocation(head.getX() - GameFrame.FIELD_WIDTH_PX, head.getY());
        }
        if (pressedDirections.size() > 1) {
            pressedDirections.remove(0);
        }

        if (head.getLocation().equals(new Point(103 * GameFrame.FIELD_WIDTH_PX, (27 + 3) * GameFrame.FIELD_HEIGHT_PX)) && allKeysCollected) {
            enteringNewLevel = true;
            System.out.println("WON");
            newLevel();
        }

        if (head.getLocation().equals(boost.getLocation())) {
            eatBoost();
        }

        if (!isInvinceble && !enteringNewLevel) {
            testIfDied(head.getX() / GameFrame.FIELD_WIDTH_PX, (head.getY() - 60) / GameFrame.FIELD_HEIGHT_PX);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void newLevel() {
        someStuff();
        isInvinceble = true;
        startMoving = false;
        timer.cancel();

        switch (gameStuff.getCurrentLevel()) {
            case LEVEL1 -> newLevelSettings(Levels.LEVEL2, 2);
            case LEVEL2 -> newLevelSettings(Levels.LEVEL3, 3);
            case LEVEL3 -> newLevelSettings(Levels.LEVEL4, 4);
            case LEVEL4 -> newLevelSettings(Levels.LEVEL5, 5);
            case LEVEL5 -> newLevelSettings(Levels.LEVEL6, 6);
            case LEVEL6 -> newLevelSettings(Levels.LEVEL7, 7);
            case LEVEL7 -> newLevelSettings(Levels.LEVEL8, 8);
            case LEVEL8 -> newLevelSettings(Levels.LEVEL9, 9);
            case LEVEL9 -> newLevelSettings(Levels.LEVEL10, 10);
            case LEVEL10 -> newLevelSettings(Levels.LEVEL11, 11);
            //TODO IMPORTANT FOR NEW LEVELS!
        }
    }

    /**
     * This method sets the current level up one time if a new level is reached.
     * The method which is called by a timer in the GameFrame class will notice that and start a new level.
     */
    private void newLevelSettings(final Levels level, final int reachedLevel) {
        gameStuff.setCurrentLevel(level);
        if (settings.getUnlockedLevel() < reachedLevel) {
            settings.setUnlockedLevel(reachedLevel);
            gameStuff.setSendUnlockMessage(true);
        }else {
            gameStuff.setSendUnlockMessage(false);
        }
        allKeysCollected = false;
        new SettingsManager().save(settings);
    }

    /**This method will be called from the GameFrame class after it has repainted itself for the label to be shown
     * when the player has the chance to see it and not when the level is repainting. It will send a AchievementThing
     * based on the Colors of the unlocked Skin*/
    public void sendUnlockThing(final int reachedLevel) {

        for (Skins skin : Skins.values()) {

            if (reachedLevel == skin.getUnlockNumber()){
                new AchievementSlideThing(settings, gameStuff, "New skin unlocked: " + skin.name(), panel,
                        skin.getTailColor(), new Color(96, 96, 96), skin.getHeadColor()).act();
            }
        }
    }

    /**
     * Does some settings in the gameStuff instance
     */
    private void someStuff() {
        System.out.println("some stuff");
        gameStuff.setKeyAmount(0);
        gameStuff.setTimeElapsed(playtimeManager.getTime());
        gameStuff.setLives(gameStuff.getLives());
    }

    /**
     * Will change the highscore if a new points all time high is achieved
     */
    private void highscore() {
        if (gameStuff.getPoints() > settings.getHighestPoints()) {
            settings.setHighestPoints(gameStuff.getPoints() * settings.getMode().getModeMultiplier());
            settings.setHighScoreTime(playtimeManager.setTime(gameStuff.getTimeElapsed()));
            settings.setHighScoreLevel(gameStuff.getCurrentLevel());
            settings.setHighScoreMode(settings.getMode());
            new SettingsManager().save(settings);
        }
    }

    /**
     * Moves all the tails in the tails list to the position of the next tail
     */
    private void moveTails() {

        int moveToX = head.getX();
        int moveToY = head.getY();

        for (SnakeTail tail : tails) {
            int tailX = tail.getX();
            int tailY = tail.getY();
            tail.setLocation(moveToX, moveToY);
            moveToX = tailX;
            moveToY = tailY;
        }
    }

    /**
     * Method that is called when the player died
     */
    private void died() {
        playtimeManager.stopTimer();
        System.out.println("timer stopped");
        boost.setVisible(false);

        ListIterator<SnakeTail> iterator = tails.listIterator(tails.size());

        Timer removeTimer = new Timer();
        removeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (iterator.hasPrevious()) {
                    SnakeTail tail = iterator.previous();
                    panel.remove(tail);
                    panel.revalidate();
                    panel.repaint();
                    iterator.remove();
                } else {
                    cancel();
                }
            }
        }, 0, settings.getMode().getSpeed() / 4);

        highscore();

        new DiedFrame(settings, gameStuff, gameFrame);
    }

    private void testIfDied(int x, int y) {

        if (gameStuff.getObstacles()[x][y] || snakeHitItself()) {
            pauseTimer();
            startMoving = false;
            gameStuff.setLives(gameStuff.getLives() - 1);
            if (gameStuff.getLives() > 0) {
                respawn();
                System.out.println("respawn");
            } else {
                died();
                System.out.println("died");
            }
            lives.setText("lives: " + gameStuff.getLives());
        }
    }

    /**
     * Method that looks if the snake hits itself with its head
     */
    private boolean snakeHitItself() {

        for (SnakeTail tail : tails) {

            if (head.getLocation().equals(tail.getLocation())) {
                return true;
            }

        }
        return false;
    }

    /**
     * Called when the snake has to respawn and calls the respawnTheSnake method
     */
    public void respawn() {
        pressedDirections.clear();
        pressedDirections.add(MovingDirections.RIGHT);
        direction = MovingDirections.RIGHT;
        respawnTheSnake();
    }

    private void respawnTheSnake() {
        startMoving = false;
        int index = 0;

        if (enteringNewLevel) {
            ListIterator<SnakeTail> iterator = tails.listIterator(2);
            for (int i = 1040; i > 999; i -= GameFrame.FIELD_WIDTH_PX) {
                tails.get(index).setLocation(i, 600);
                panel.revalidate();
                panel.repaint();
                index++;
            }
            head.setLocation(1060, 600);
            while (iterator.hasNext())
                iterator.next().setLocation(tails.get(2).getLocation());
            enteringNewLevel = false;
        } else {

            ListIterator<SnakeTail> iterator = tails.listIterator(tails.size());

            Timer respawnTimer = new Timer();
            respawnTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    if (iterator.hasPrevious()) {
                        SnakeTail tail = iterator.previous();
                        if (!tail.equals(tails.get(0)) &&
                                !tail.equals(tails.get(1)) &&
                                !tail.equals(tails.get(2))) {
                            tail.setLocation(1000, 600);
                            tail.setVisible(false);
                            panel.revalidate();
                            panel.repaint();
                        } else {
                            cancel();
                            replaceTailAndHead();
                        }
                    } else {
                        cancel();
                    }
                }
            }, 0, settings.getMode().getSpeed() / 2);
        }
    }

    private void replaceTailAndHead() {
        int index = 0;
        for (int i = 1040; i > 999; i -= GameFrame.FIELD_WIDTH_PX) {
            tails.get(index).setLocation(i, 600);
            panel.revalidate();
            panel.repaint();
            index++;
        }
        head.setLocation(1060, 600);
        for (SnakeTail tail : tails) {
            tail.setVisible(true);
        }
        startMoving = true;
    }


    /**
     * This will pause the timer which moves the snake, causing the snake to stop
     */
    public void pauseTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * Opens the pause menu for the player when the Esc button is pressed
     */
    private void openPauseMenu() {
        highscore();
        pauseTimer();
        playtimeManager.stopTimer();
        new PauseFrame(settings, this, gameFrame, gameStuff);
    }

    /**
     * This will start the timer again if the timer was paused by the menu
     */
    public void resumeTimer() {
        if (timerStartedOnce) {
            moveSnake();
            playtimeManager.startTimer();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Method which allows other classes to add tails to the tails list
     */
    public void addTail(final SnakeTail pTail) {
        tails.add(pTail);
    }

    public List<SnakeTail> getTails() {
        return tails;
    }
}
