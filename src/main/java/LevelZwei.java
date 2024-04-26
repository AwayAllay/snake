public class LevelZwei extends GameFrame{
    private final GameStuff gameStuff;

    public LevelZwei(final Settings settings, final GameStuff gameStuff){

        super(settings, gameStuff);

        this.gameStuff = gameStuff;
        gameStuff.setCurrentLevel(Levels.LEVEL2);

        setLevel("LEVEL 2");
        startTimer();

        this.revalidate();
        this.repaint();
    }
}
