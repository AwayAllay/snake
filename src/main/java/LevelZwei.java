public class LevelZwei extends GameFrame{

    public LevelZwei(final Settings settings, final GameStuff gameStuff){

        super(settings, gameStuff);

        gameStuff.setCurrentLevel(Levels.LEVEL2);

        setLevel("LEVEL 2");
        startTimer();

        this.revalidate();
        this.repaint();
    }
}
