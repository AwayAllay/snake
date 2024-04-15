public class Main {

    private static String skin = "default";
    private static String mode = "noob";
    public static void main(String[] args) {
        new LaunchFrame();

    }

    public static String getMode() {
        return mode;
    }

    public static void setMode(String mode) {
        Main.mode = mode;
    }

    public static String getSkin() {
        return skin;
    }

    public static void setSkin(String skin) {
        Main.skin = skin;
    }
}
