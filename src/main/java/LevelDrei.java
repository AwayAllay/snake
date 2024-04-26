public class LevelDrei extends GameFrame{
    private final GameStuff gameStuff;

    public LevelDrei(final Settings settings, final GameStuff gameStuff){

        super(settings, gameStuff);

        this.gameStuff = gameStuff;
        gameStuff.setCurrentLevel(Levels.LEVEL3);

        setLevel("LEVEL 3");
        startTimer();

        this.revalidate();
        this.repaint();
    }
}
