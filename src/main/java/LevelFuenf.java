public class LevelFuenf extends GameFrame{

    public LevelFuenf(final Settings settings, final GameStuff gameStuff){

        super(settings, gameStuff);

        gameStuff.setCurrentLevel(Levels.LEVEL5);

        setLevel("LEVEL 5");
        startTimer();

        this.revalidate();
        this.repaint();
    }

}
