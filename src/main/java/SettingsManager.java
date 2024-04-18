import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsManager {

    private static final String FILE_NAME = "snake.properties";

    public Settings load() {

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(FILE_NAME));
        } catch (FileNotFoundException e) {
            return new Settings();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File not found or not readable!", "ERROR", JOptionPane.WARNING_MESSAGE);
        }


        Settings settings = new Settings();

        Skins skin = Skins.valueOf(properties.getProperty("skin"));
        Modes mode = Modes.valueOf(properties.getProperty("mode"));
        int unlockedLevel = Integer.valueOf(properties.getProperty("unlockedLevel"));

        if (skin != null) {
            settings.setSkin(skin);
        }
        if (mode != null) {
            settings.setMode(mode);
        }
        if (unlockedLevel != 0){
            settings.setUnlockedLevel(unlockedLevel);
        }

        return settings;
    }

    public void save(final Settings pSettings) {

        Properties properties = new Properties();

        properties.setProperty("skin", String.valueOf(pSettings.getSkin()));
        properties.setProperty("mode", String.valueOf(pSettings.getMode()));
        properties.setProperty("unlockedLevel", String.valueOf(pSettings.getUnlockedLevel()));

        try {
            properties.store(new FileOutputStream(FILE_NAME), "The set settings of the user.");
        } catch (IOException e) {
            System.out.println("Sth went wrong");
            JOptionPane.showMessageDialog(null, "File not found or not readable!", "ERROR", JOptionPane.WARNING_MESSAGE);
        }

    }

}
