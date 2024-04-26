public class LevelEins extends GameFrame{
    private final GameStuff gameStuff;

    public LevelEins(final Settings settings, final GameStuff gameStuff){

        super(settings, gameStuff);

        this.gameStuff = gameStuff;
        gameStuff.setCurrentLevel(Levels.LEVEL1);

        setLevel("LEVEL 1");
        startTimer();

        this.revalidate();
        this.repaint();
    }
}
