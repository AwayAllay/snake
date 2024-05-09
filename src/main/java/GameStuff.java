import java.util.ArrayList;
import java.util.List;

public class GameStuff {

    private Levels currentLevel;
    private int lives;
    private long points;
    private int keyAmount;
    private int timeElapsed;
    private boolean[][] obstacles;
    private boolean sendUnlockMessage = false;
    private List<Popup> popups;
    private boolean isLauncherMusicPlaying = false;
    private final Playsound playLauncherBackgroundMusic = new Playsound("Launcher.wav");
    private boolean isGameMusicPLaying = false;
    private final Playsound playGameBackgroundMusic = new Playsound("GameBackground.wav");

    public GameStuff() {
        timeElapsed = 1;
        currentLevel = Levels.LEVEL1;
        lives = 10;
        keyAmount = 0;
        points = 0;
        obstacles = null;
        popups  = new ArrayList<>();
    }


    public boolean isGameMusicPLaying() {
        return isGameMusicPLaying;
    }

    public void setGameMusicPLaying(boolean gameMusicPLaying) {
        isGameMusicPLaying = gameMusicPLaying;
    }

    public Playsound getPlayGameBackgroundMusic() {
        return playGameBackgroundMusic;
    }

    public Playsound getPlayLauncherBackgroundMusic() {
        return playLauncherBackgroundMusic;
    }

    public boolean isLauncherMusicPlaying() {
        return isLauncherMusicPlaying;
    }

    public void setLauncherMusicPlaying(boolean launcherMusicPlaying) {
        isLauncherMusicPlaying = launcherMusicPlaying;
    }

    public List<Popup> getPopups() {
        return popups;
    }

    public boolean[][] getObstacles() {
        return obstacles;
    }

    public void setObstacles(boolean[][] obstacles) {
        System.out.println("obstacles set");
        this.obstacles = obstacles;
    }
    /**This returns true if the player has reached a level for the first time and could so potentially has unlocked
     * a new skin*/
    public boolean isSendUnlockMessage() {
        return sendUnlockMessage;
    }

    /**This is set to true if the player has reached a level for the first time and could so potentially has unlocked
     * a new skin*/
    public void setSendUnlockMessage(boolean sendUnlockMessage) {
        this.sendUnlockMessage = sendUnlockMessage;
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

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}
