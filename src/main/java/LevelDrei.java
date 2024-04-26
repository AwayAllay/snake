public class LevelDrei extends GameFrame{

    public LevelDrei(final Settings settings, final GameStuff gameStuff){

        super(settings, gameStuff);

        gameStuff.setCurrentLevel(Levels.LEVEL3);

        setLevel("LEVEL 3");
        startTimer();

        this.revalidate();
        this.repaint();
    }
}
