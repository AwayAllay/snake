public class LevelZwei extends GameFrame{
    private static boolean[][] obstacles;
    private final GameStuff gameStuff;

    public LevelZwei(final Settings settings, final GameStuff gameStuff){

        super(settings, gameStuff);

        this.gameStuff = gameStuff;
        gameStuff.setCurrentLevel(Levels.LEVEL2);

        obstacles = translateLevel("level2.txt");
        setLevel("LEVEL 2");
        startTimer();
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
    public static boolean[][] getObstacles() {
        return obstacles;
    }
}
