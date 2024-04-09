import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    /**0 == North, 1 == East, 2 == South, 3 == West*/
    private int movingDirection = 1;

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

                break;
            //A
            case 65:

                break;
            //S
            case 83:

                break;
            //D
            case 68:

                break;
            //Leertaste
            case 32:

                break;
            //Esc
            case 27:

                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
