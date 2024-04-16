import java.awt.*;

public enum Skins {
    DEFAULT(new Color(0, 204, 0), new Color(0, 102, 0)),
    BLUE(new Color(0, 255, 255), new Color(0, 102, 102)),
    BROWN(new Color(95, 48, 0), new Color(176, 88, 0)),
    BLACK(new Color(0, 0, 0), new Color(200, 200, 200)),
    RED(new Color(204, 0, 0), new Color(55, 0, 0)),
    GOLD(new Color(255, 255, 0), new Color(153, 153, 0)),
    PURPLE(new Color(204, 0, 102), new Color(102, 0, 51)),
    GRAY(new Color(96, 96, 96), new Color(32, 32, 32)),
    NOT_UNLOCKED(new Color(0, 0, 0), new Color(0, 0, 0));


    public final Color tailColor;
    public final Color headColor;

    Skins(final Color tailColor, final Color headColor) {
        this.tailColor = tailColor;
        this.headColor = headColor;
    }
}
