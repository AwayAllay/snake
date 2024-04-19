public class LevelEins extends GameFrame{

    public LevelEins(final Settings settings){

        super(settings);
        translateLevel("level1.txt");
        setLevel("LEVEL 1");
        startTimer(1);
    }

}
