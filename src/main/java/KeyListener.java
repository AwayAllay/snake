import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class KeyListener implements java.awt.event.KeyListener {

    /**0 == North, 1 == East, 2 == South, 3 == West*/
    private int movingDirection = 1;

    private List<SnakeTail> snakeTails = new LinkedList<>();


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
                movingDirection = 0;
                break;
            //A
            case 65:
                movingDirection = 3;
                break;
            //S
            case 83:
                movingDirection = 2;
                break;
            //D
            case 68:
                movingDirection = 1;
                break;
            //Esc
            case 27:
                openPauseMenu();
                break;
        }
    }

    private void moveSnake(){

    }

    private void openPauseMenu() {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public List<SnakeTail> getSnakeTails() {
        return snakeTails;
    }
}
