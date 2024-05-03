public class Settings {

    private Skins skin = Skins.DEFAULT;
    private Modes mode = Modes.NOOB;
    private int unlockedLevel = 1;
    private long highestPoints = 0L;
    private String highScoreTime = "00:00:00";
    private Levels highScoreLevel = Levels.LEVEL1;
    private Modes highScoreMode = Modes.GOD;

    public String getHighScoreTime() {
        return highScoreTime;
    }

    public void setHighScoreTime(String highScoreTime) {
        this.highScoreTime = highScoreTime;
    }

    public Levels getHighScoreLevel() {
        return highScoreLevel;
    }

    public void setHighScoreLevel(Levels highScoreLevel) {
        this.highScoreLevel = highScoreLevel;
    }

    public long getHighestPoints() {
        return highestPoints;
    }

    public void setHighestPoints(long highestPoints) {
        this.highestPoints = highestPoints;
    }

    public Skins getSkin() {
        return skin;
    }

    public void setSkin(final Skins skin) {
        this.skin = skin;
    }

    public Modes getMode() {
        return mode;
    }

    public void setMode(final Modes mode) {
        this.mode = mode;
    }

    public int getUnlockedLevel() {
        return unlockedLevel;
    }

    public void setUnlockedLevel(int unlockedLevel) {
        this.unlockedLevel = unlockedLevel;
    }

    public Modes getHighScoreMode() {
        return highScoreMode;
    }

    public void setHighScoreMode(Modes highScoreMode) {
        this.highScoreMode = highScoreMode;
    }
}
