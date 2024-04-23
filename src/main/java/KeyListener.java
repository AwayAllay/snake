import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;

public class KeyListener implements java.awt.event.KeyListener {

    /**
     * 0 == North, 1 == East, 2 == South, 3 == West
     */
    private MovingDirections direction = MovingDirections.RIGHT;
    private Timer timer;
    private final JLabel head;
    private JLabel boost;
    private boolean allKeysCollected;
    private final JFrame gameFrame;
    private final JPanel panel;
    private final JLabel points;
    private final JLabel lives;
    private final JLabel keys;
    private final Settings settings;
    private final GameStuff gameStuff;
    private final List<SnakeTail> tails = new LinkedList<>();
    private boolean startMoving = true;
    private boolean timerStartedOnce;
    private IngameBoost currentBoost;

    /**
     * Constructor
     */
    public KeyListener(JLabel head, JPanel panel, Settings settings, JFrame gameFrame, GameStuff gameStuff, JLabel points, JLabel lives, JLabel keys) {
        this.gameStuff = gameStuff;
        this.settings = settings;
        this.gameFrame = gameFrame;
        this.head = head;
        this.panel = panel;
        this.points = points;
        this.lives = lives;
        this.keys = keys;
        timerStartedOnce = false;
        currentBoost = IngameBoost.REGULAR_BOOST;
        allKeysCollected = false;
        boost = new JLabel();
        boost.setLocation(-200, -200);
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
            case 87 -> direction = MovingDirections.UP;

            //A
            case 65 -> direction = MovingDirections.LEFT;

            //S
            case 83 -> direction = MovingDirections.DOWN;

            //D
            case 68 -> direction = MovingDirections.RIGHT;

            //Esc
            case 27 -> openPauseMenu();

            //Space
            case 32 -> {
                if (startMoving) {
                    moveSnake();
                    startMoving = false;
                    spawnBoost();
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
        if (randomNumber <= 5) {
            currentBoost = IngameBoost.KEY_BOOST;
        } else if (randomNumber > 5 && randomNumber <= 35) {
            currentBoost = IngameBoost.REGULAR_BOOST;
        } else if (randomNumber > 35 && randomNumber <= 60) {
            currentBoost = IngameBoost.NICE_BOOST;
        } else if (randomNumber > 60 && randomNumber <= 63) {
            currentBoost = IngameBoost.MYTHICAL_BOOST;
        } else if (randomNumber > 63 && randomNumber <= 70) {
            currentBoost = IngameBoost.BAD_BOOST;
        } else if (randomNumber > 70 && randomNumber <= 80) {
            currentBoost = IngameBoost.HEALTH_BOOST;
        } else if (randomNumber > 80 && randomNumber <= 95) {
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
        panel.add(boost);

        setRandomBoostLocation();

        //TODO random Location for boost

    }

    /**Sets a radom Location for the boost and looks if it would be on the snake or on one of
     * the obstacles. If so it will create a new Location for the boost by recursion.*/
    private void setRandomBoostLocation() {
        int randomX = new Random().nextInt(105);
        int randomY = new Random().nextInt(51) + 4;

        boolean[][] obstacles = getObstacles(gameStuff.getCurrentLevel());

        if (obstacles[randomX][randomY] || boostOnSnake(randomX, randomY)){
            setRandomBoostLocation();
        }else {
            boost.setLocation(randomX * GameFrame.FIELD_WIDTH_PX, randomY * GameFrame.FIELD_HEIGHT_PX);
        }

    }

    /**Tests if the boost would spawn on the snake.
     * If so it returns true, otherwise false.*/
    private boolean boostOnSnake(final int x, final int y) {

        for (SnakeTail tail : tails) {
            if (x == tail.getX() || y == tail.getY()) {
                return true;
            }
        }
        return false;
    }

    private void eatBoost() {

        if (gameStuff.getKeyAmount() >= 5) {
            allKeysCollected = true;
        }

        if (!allKeysCollected) {

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
            spawnBoost();
        } else {
            openDoor();
        }
    }

    private void openDoor() {
        //TODO OPEN THE DOOR
    }

    private void moveAction() {

        moveTails();

        switch (direction) {
            case UP -> head.setLocation(head.getX(), head.getY() - GameFrame.FIELD_HEIGHT_PX);
            case RIGHT -> head.setLocation(head.getX() + GameFrame.FIELD_WIDTH_PX, head.getY());
            case DOWN -> head.setLocation(head.getX(), head.getY() + GameFrame.FIELD_HEIGHT_PX);
            case LEFT -> head.setLocation(head.getX() - GameFrame.FIELD_WIDTH_PX, head.getY());
        }

        testIfDied(head.getX() /  GameFrame.FIELD_WIDTH_PX,(head.getY() - 60) / GameFrame.FIELD_HEIGHT_PX);
        if (head.getLocation().equals(boost.getLocation())) {
            eatBoost();
        }
        panel.revalidate();
        panel.repaint();
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
        new DiedFrame(settings, gameStuff);
        //TODO DIED
    }

    /**
     * This method will decide whether the player died or respawns or just normally moved.
     * By getting all the fields from the .txt file for the level, this will put them in a 2-dimensional
     * Array, which will then look up the field with the coordinates of the snake.
     * Also looks if the head position equals a position of the snake itself.
     */
    private void testIfDied(int x, int y) {

        boolean[][] obstacles = getObstacles(gameStuff.getCurrentLevel());

        if (obstacles[x][y] || snakeHitItself()) {
            pauseTimer();
            gameStuff.setLives(gameStuff.getLives() - 1);
            if (gameStuff.getLives() > 0) {
                //respawn();
                System.out.println("respawn");
            } else {
                //died();
                System.out.println("died");
            }
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

    private void respawn() {
        resumeTimer();
        //TODO RESPAWN
    }

    /**
     * This will get the 2-dimentional Array depending on the level
     */
    private boolean[][] getObstacles(final Levels level) {
        switch (level) {

            //TODO static?

            case LEVEL1 -> {
                return LevelEins.getObstacles();
            }
            case LEVEL2 -> {
                return LevelZwei.getObstacles();
            }
            default -> {
                System.out.println("Bla");
                return LevelEins.getObstacles();
            }
        }
    }

    /**
     * Sets the speed for the timer moving the snake depending on what mode is selected
     */
    private int getSpeed() {

        switch (settings.getMode()) {

            case BEGINNER -> {
                return 160;
            }
            case ADULT -> {
                return 150;
            }
            case MASTER -> {
                return 130;
            }
            case GOD -> {
                return 110;
            }
            default -> {
                return 200;
            }

        }
    }

    /**
     * This will pause the timer which moves the snake, causing the snake to stop
     */
    private void pauseTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * Opens the pause menu for the player when the Esc button is pressed
     */
    private void openPauseMenu() {
        pauseTimer();
        new PauseFrame(settings, this, gameFrame, gameStuff);
        //TODO Pause Timer for time
    }

    /**
     * This will start the timer again if the timer was paused by the menu
     */
    public void resumeTimer() {
        if (timerStartedOnce) {
            moveSnake();
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

}
