import java.util.LinkedList;

public class LevelEins extends GameFrame{
    private LinkedList<SnakeTail> tails = (LinkedList<SnakeTail>) new KeyListener().getSnakeTails();

    public LevelEins(){
        setLevel("LEVEL 1");
        startTimer(0);



    }

    private void addTail(final int pX, final int pY) {

        SnakeTail tail = new SnakeTail(pX, pY, 10, 10);
        tails.add(tail);
        getPanel().add(tail);
    }

}
