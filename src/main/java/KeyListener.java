import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class KeyListener implements java.awt.event.KeyListener {

    /**
     * 0 == North, 1 == East, 2 == South, 3 == West
     */
    private MovingDirections direction = MovingDirections.RIGHT;
    private Timer timer;
    private final JLabel head;
    private final JFrame gameFrame;
    private final JPanel panel;
    private final Settings settings;
    private final GameStuff gameStuff;
    private final List<SnakeTail> tails = new LinkedList<>();
    private boolean startMoving = true;
    private boolean timerStartedOnce;

    public KeyListener(JLabel head, JPanel panel, Settings settings, JFrame gameFrame, GameStuff gameStuff) {
        this.gameStuff = gameStuff;
        this.settings = settings;
        this.gameFrame = gameFrame;
        this.head = head;
        this.panel = panel;
        timerStartedOnce = false;
    }

    private void increaseSnakeLenght(){

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
                }
            }
        }
    }

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

    private void moveAction() {

        moveTails();

        switch (direction) {
            case UP -> head.setLocation(head.getX(), head.getY() - GameFrame.FIELD_HEIGHT_PX);
            case RIGHT -> head.setLocation(head.getX() + GameFrame.FIELD_WIDTH_PX, head.getY());
            case DOWN -> head.setLocation(head.getX(), head.getY() + GameFrame.FIELD_HEIGHT_PX);
            case LEFT -> head.setLocation(head.getX() - GameFrame.FIELD_WIDTH_PX, head.getY());
        }

        //Position position = new Position(head.getX(), head.getY());
        //testIfDied(position.getFieldX(), position.getFieldY());
        panel.revalidate();
        panel.repaint();
    }

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

    private void died() {
        new DiedFrame(settings, gameStuff);
    }

    private void testIfDied(int x, int y) {

        boolean[][] obstacles = getObstacles(gameStuff.getCurrentLevel());

        //TODO ARRAYSINDESOUTOFBOUNDSEXCEPTION
        if (obstacles[x][y] || snakeHitItself()) {
            pauseTimer();
            gameStuff.setLives(gameStuff.getLives() - 1);
            if (gameStuff.getLives() > 0) {
                respawn();
            } else {
                died();
            }
        }
    }

    private boolean snakeHitItself() {

        for (SnakeTail tail: tails) {

            if (head.getLocation() == tail.getLocation()){
                return true;
            }

        }
        return false;
    }

    private void respawn() {
        resumeTimer();
    }

    private boolean[][] getObstacles(final Levels level) {
        switch (level) {

            case LEVEL1 -> {
                return LevelEins.getObstacles();
            }
            default -> {
                System.out.println("Bla");
                return LevelEins.getObstacles();
            }
        }
    }

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

    private void pauseTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void openPauseMenu() {
        pauseTimer();
        new PauseFrame(settings, this, gameFrame, gameStuff);
        //TODO Pause Timer for time
    }

    public void resumeTimer() {
        if (timerStartedOnce){
            moveSnake();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public List<SnakeTail> getTails() {
        return tails;
    }

    public void addTail(final SnakeTail pTail){
        tails.add(pTail);
    }

}
