import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class KeyListener implements java.awt.event.KeyListener {

    /**0 == North, 1 == East, 2 == South, 3 == West*/
    private MovingDirections direction = MovingDirections.RIGHT;
    private final JLabel head;

    private final JPanel panel;

    public KeyListener(JLabel head, JPanel panel) {
        this.head = head;
        this.panel = panel;
    }

    /** Keys:
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

        switch (e.getKeyCode()){

            //W
            case 87:
                direction = MovingDirections.UP;
                break;
            //A
            case 65:
                direction = MovingDirections.LEFT;
                break;
            //S
            case 83:
                direction = MovingDirections.DOWN;
                break;
            //D
            case 68:
               direction = MovingDirections.RIGHT;
                break;
            //Esc
            case 27:
                openPauseMenu();
                break;
            //Space
            case 32:
                do {
                    moveSnake();
                }while (false);
                break;
        }
    }

    private void moveSnake(){
        Timer timer = new Timer();

        int speed = getSpeed();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveAction();
            }
        }, 0, speed);
    }

    private void moveAction() {
        switch (direction){

            case UP:
                head.setLocation(head.getX(), head.getY() - 20);
                break;
            case RIGHT:
                head.setLocation(head.getX() + 20, head.getY());
                break;
            case DOWN:
                head.setLocation(head.getX(), head.getY() + 20);
                break;
            case LEFT:
                head.setLocation(head.getX() - 20, head.getY());
                break;

        }
        panel.revalidate();
        panel.repaint();
    }

    private int getSpeed() {

        int speed;
        Settings settings = new Settings();

        switch (settings.getMode()){

            case BEGINNER -> {
                System.out.println("B");
                return speed = 160;
            }
            case ADULT -> {
                System.out.println("A");
                return speed = 150;
            }
            case MASTER -> {
                System.out.println("M");
                return speed = 130;
            }
            case GOD -> {
                System.out.println("G");
                return speed = 110;
            }
            default -> {
                System.out.println("N");
                return speed = 200;
            }

        }
    }

    private void openPauseMenu() {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public MovingDirections getDirection() {
        return direction;
    }
}
