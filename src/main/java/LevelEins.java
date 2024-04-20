public class LevelEins extends GameFrame{

    public LevelEins(final Settings settings){

        super(settings);
        boolean[][] obstacles = translateLevel("level1.txt");
        setLevel("LEVEL 1");
        startTimer(1);
        printObstacles(obstacles);
    }

    private void printObstacles(boolean[][] obstacles) {

        for (int y = 0; y < NUM_FIELDS_VERT; y++) {
            for (int x = 0; x < NUM_FIELDS_HORIZ; x++) {
                System.out.print(obstacles[x][y] ? "X" : " ");
            }
            System.out.println();
        }
        System.out.flush();
    }

}
