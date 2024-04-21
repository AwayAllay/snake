public class GameStuff {

    private Levels currentLevel;
    private int lives;
    private int points;
    private int keyAmount;

    public GameStuff() {
        currentLevel = Levels.LEVEL1;
        lives = 5;
        keyAmount = 0;
        points = 0;
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
}
