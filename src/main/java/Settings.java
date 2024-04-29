public class Settings {

    private Skins skin = Skins.DEFAULT;
    private Modes mode = Modes.NOOB;
    private int unlockedLevel = 1;
    private int highestPoints = 0;
    private String highScoreTime = "00:00:00";
    private Levels highScoreLevel = Levels.LEVEL1;

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

    public int getHighestPoints() {
        return highestPoints;
    }

    public void setHighestPoints(int highestPoints) {
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
}
