public class Settings {

    private Skins skin = Skins.DEFAULT;
    private Modes mode = Modes.NOOB;
    private int unlockedLevel = 50;

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
