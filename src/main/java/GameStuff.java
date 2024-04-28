public class GameStuff {

    private Levels currentLevel;
    private int lives;
    private int points;
    private int keyAmount;
    private int timeElapsed;
    private boolean[][] obstacles;

    public GameStuff() {
        timeElapsed = 1;
        currentLevel = Levels.LEVEL1;
        lives = 5;
        keyAmount = 0;
        points = 0;
        obstacles = null;
    }

    public boolean[][] getObstacles() {
        return obstacles;
    }

    public void setObstacles(boolean[][] obstacles) {
        System.out.println("obstacles set");
        this.obstacles = obstacles;

        //TODO tracking
        for (int y = 0; y < 55; y++) {
            for (int x = 0; x < 104; x++) {
                if (obstacles[x][y]){
                    System.out.print("X");
                }else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        
    }

    public Levels getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Levels currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getKeyAmount() {
        return keyAmount;
    }

    public void setKeyAmount(int keyAmount) {
        this.keyAmount = keyAmount;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}
