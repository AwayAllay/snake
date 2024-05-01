import java.awt.*;

public enum Skins {
    DEFAULT(new Color(0, 204, 0), new Color(0, 102, 0), 1),
    BLUE(new Color(0, 255, 255), new Color(0, 102, 102), 3),
    BROWN(new Color(95, 48, 0), new Color(176, 88, 0), 5),
    BLACK(new Color(0, 0, 0), new Color(200, 200, 200), 10),
    RED(new Color(204, 0, 0), new Color(55, 0, 0), 15),
    GOLD(new Color(255, 255, 0), new Color(153, 153, 0), 20),
    PURPLE(new Color(204, 0, 102), new Color(102, 0, 51), 25),
    GRAY(new Color(96, 96, 96), new Color(32, 32, 32), 30),
    NOT_UNLOCKED(new Color(0, 0, 0), new Color(0, 0, 0), 10000);


    private final Color tailColor;
    private final Color headColor;
    private final int unlockNumber;

    Skins(final Color tailColor, final Color headColor, int unlockNumber) {
        this.tailColor = tailColor;
        this.headColor = headColor;
        this.unlockNumber = unlockNumber;
    }

    public Color getTailColor() {
        return tailColor;
    }

    public Color getHeadColor() {
        return headColor;
    }

    public int getUnlockNumber() {
        return unlockNumber;
    }
}
