public class LevelEins extends GameFrame{

    public LevelEins(final Settings settings, final GameStuff gameStuff){

        super(settings, gameStuff);

        gameStuff.setCurrentLevel(Levels.LEVEL1);

        setLevel("LEVEL 1");
        startTimer();

        this.revalidate();
        this.repaint();
    }
}
