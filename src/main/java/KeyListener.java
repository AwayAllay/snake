import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class KeyListener implements java.awt.event.KeyListener {

    /**
     * 0 == North, 1 == East, 2 == South, 3 == West
     */
    private MovingDirections direction = MovingDirections.RIGHT;
    private Timer timer;
    private boolean enteringNewLevel = false;
    private boolean isInvinceble = false;
    private final JLabel head;
    private final JLabel boost;
    private boolean allKeysCollected;
    private final JFrame gameFrame;
    private final JPanel panel;
    private final JLabel points;
    private final JLabel lives;
    private final JLabel keys;
    private final Settings settings;
    private final GameStuff gameStuff;
    private final PlaytimeManager playtimeManager;
    private final List<SnakeTail> tails = new LinkedList<>();
    private boolean startMoving = true;
    private boolean timerStartedOnce;
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

        update();
    }

    public void update() {
        timerStartedOnce = false;
        currentBoost = IngameBoost.REGULAR_BOOST;
        allKeysCollected = false;
        isInvinceble = false;
        spawnBoost();
        System.out.println("Updated that shit");
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

            //W
            case 87 -> {
                if (direction != MovingDirections.DOWN) {
                    direction = MovingDirections.UP;
                }
            }
            //A
            case 65 -> {
                if (direction != MovingDirections.RIGHT) {
                    direction = MovingDirections.LEFT;
                }
            }
            //S
            case 83 -> {
                if (direction != MovingDirections.UP) {
                    direction = MovingDirections.DOWN;
                }
            }
            //D
            case 68 -> {
                if (direction != MovingDirections.LEFT) {
                    direction = MovingDirections.RIGHT;
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
        }
    }

    /**
     * This starts the timer which will depending on the mode call moveAction() from 110-200 milliseconds
     */
    private void moveSnake() {
        timer = new Timer();
        timerStartedOnce = true;

        int speed = getSpeed();

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
        boost.setBounds(500, 500, GameFrame.FIELD_WIDTH_PX, GameFrame.FIELD_HEIGHT_PX);
        boost.setVisible(true);
        panel.add(boost);

        setRandomBoostLocation();

    }

    /**
     * Sets a radom Location for the boost and looks if it would be on the snake or on one of
     * the obstacles. If so it will create a new Location for the boost by recursion.
     */
    private void setRandomBoostLocation() {
        int randomX = new Random().nextInt(103) + 1;
        int randomY = new Random().nextInt(50) + 4;

        System.out.println(gameStuff.getObstacles()[randomX][randomY]);

        if (gameStuff.getObstacles()[randomX][randomY] || boostOnSnake(randomX, randomY)) {
            setRandomBoostLocation();
        } else {
            boost.setLocation(randomX * GameFrame.FIELD_WIDTH_PX, randomY * GameFrame.FIELD_HEIGHT_PX);
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

        gameStuff.setPoints(gameStuff.getPoints() + currentBoost.getPoints());
        gameStuff.setLives(gameStuff.getLives() + currentBoost.getHealthBoost());
        increaseSnakeLength(currentBoost.getLengthBoost());
        gameStuff.setKeyAmount(gameStuff.getKeyAmount() + currentBoost.getKeyBoost());

        points.setText(String.valueOf(gameStuff.getPoints()));
        lives.setText("lives: " + gameStuff.getLives());
        keys.setText("keys: " + gameStuff.getKeyAmount() + "/5");

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
    }

    private void moveAction() {

        moveTails();

        switch (direction) {
            case UP -> head.setLocation(head.getX(), head.getY() - GameFrame.FIELD_HEIGHT_PX);
            case RIGHT -> head.setLocation(head.getX() + GameFrame.FIELD_WIDTH_PX, head.getY());
            case DOWN -> head.setLocation(head.getX(), head.getY() + GameFrame.FIELD_HEIGHT_PX);
            case LEFT -> head.setLocation(head.getX() - GameFrame.FIELD_WIDTH_PX, head.getY());
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
        timer.cancel();

        switch (gameStuff.getCurrentLevel()) {
            case LEVEL1 -> newLevelSettings(Levels.LEVEL2, 2);
            case LEVEL2 -> newLevelSettings(Levels.LEVEL3, 3);
            case LEVEL3 -> newLevelSettings(Levels.LEVEL4, 4);
            case LEVEL4 -> newLevelSettings(Levels.LEVEL5, 5);
            //TODO IMPORTANT FOR NEW LEVELS!
        }
    }

    private void newLevelSettings(final Levels level, final int reachedLevel) {
        gameStuff.setCurrentLevel(level);
        if (settings.getUnlockedLevel() < reachedLevel)
            settings.setUnlockedLevel(reachedLevel);
        new SettingsManager().save(settings);
    }

    private void someStuff() {
        System.out.println("some stuff");
        gameStuff.setKeyAmount(0);
        gameStuff.setTimeElapsed(playtimeManager.getTime());
        gameStuff.setLives(gameStuff.getLives());
    }

    private void highscore() {
        if (gameStuff.getPoints() > settings.getHighestPoints()){
            settings.setHighestPoints(gameStuff.getPoints());
            settings.setHighScoreTime(playtimeManager.setTime(gameStuff.getTimeElapsed()));
            settings.setHighScoreLevel(gameStuff.getCurrentLevel());
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
        }, 0, getSpeed() / 2);

        highscore();

        new DiedFrame(settings, gameStuff, gameFrame);
    }

    /**
     * This method will decide whether the player died or respawns or just normally moved.
     * By getting all the fields from the obstacles Array for the level, this will
     * then look up the field with the coordinates of the snake.
     * Also looks if the head position equals a position of the snake itself.
     */
    private void testIfDied(int x, int y) {

        if (gameStuff.getObstacles()[x][y] || snakeHitItself()) {
            pauseTimer();
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

    public void respawn() {
        respawnTheSnake();
    }

    private void respawnTheSnake() {
        startMoving = true;
        direction = MovingDirections.RIGHT;
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
                int index = 0;
                @Override
                public void run() {
                    if (iterator.hasPrevious()) {
                        SnakeTail tail = iterator.previous();
                            tail.setLocation(1000, 600);
                            panel.revalidate();
                            panel.repaint();
                    } else {
                        cancel();
                        for (int i = 1040; i > 999; i -= GameFrame.FIELD_WIDTH_PX) {
                            tails.get(index).setLocation(i, 600);
                            panel.revalidate();
                            panel.repaint();
                            index++;
                        }
                        head.setLocation(1060, 600);
                    }
                }
            }, 0, getSpeed() / 2);
        }

    }


    /**
     * Sets the speed for the timer moving the snake depending on what mode is selected
     */
    private int getSpeed() {

        switch (settings.getMode()) {

            case BEGINNER -> {
                return 120;
            }
            case ADULT -> {
                return 100;
            }
            case MASTER -> {
                return 90;
            }
            case GOD -> {
                return 60;
            }
            default -> {
                return 140;
            }

        }
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
        pauseTimer();
        playtimeManager.stopTimer();
        highscore();
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
