public class Main {

    private static Settings settings = new Settings();
    public static void main(String[] args) {

        settings = new SettingsManager().load();

        new LaunchFrame();

    }

    public static String getMode() {
        return settings.getMode();
    }

    public static void setMode(String mode) {
        settings.setMode(mode);
    }

    public static String getSkin() {
        return settings.getSkin();
    }

    public static void setSkin(String skin) {
        settings.setSkin(skin);
    }
}
