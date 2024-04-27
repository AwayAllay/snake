public class LevelVier extends GameFrame{

    public LevelVier(final Settings settings, final GameStuff gameStuff){

        super(settings, gameStuff);

        gameStuff.setCurrentLevel(Levels.LEVEL4);

        setLevel("LEVEL 4");
        startTimer();

        this.revalidate();
        this.repaint();
    }
}
