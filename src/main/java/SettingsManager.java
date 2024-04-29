import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsManager {

    private static final String FILE_NAME = "snake.properties";

    /**Loads all the saved settings from the user from the snake.properties file*/
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
        int unlockedLevel = Integer.parseInt(properties.getProperty("unlockedLevel"));
        int highestPoints = Integer.parseInt(properties.getProperty("highestPoints"));
        Levels highScoreLevel = Levels.valueOf(properties.getProperty("highScoreLevel"));
        String highScoreTime = properties.getProperty("highScoreTime");

        if (highestPoints != 0){
            settings.setHighestPoints(highestPoints);
        }

        if (highScoreLevel != null){
            settings.setHighScoreLevel(highScoreLevel);
        }

        if (highScoreTime != null){
            settings.setHighScoreTime(highScoreTime);
        }

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

    /**Saves all the settings from the user to the snake.properties file*/
    public void save(final Settings pSettings) {

        Properties properties = new Properties();

        properties.setProperty("skin", String.valueOf(pSettings.getSkin()));
        properties.setProperty("mode", String.valueOf(pSettings.getMode()));
        properties.setProperty("unlockedLevel", String.valueOf(pSettings.getUnlockedLevel()));
        properties.setProperty("highestPoints", String.valueOf(pSettings.getHighestPoints()));
        properties.setProperty("highScoreLevel", String.valueOf(pSettings.getHighScoreLevel()));
        properties.setProperty("highScoreTime", pSettings.getHighScoreTime());

        try {
            properties.store(new FileOutputStream(FILE_NAME), "The set settings of the user.");
        } catch (IOException e) {
            System.out.println("Sth went wrong");
            JOptionPane.showMessageDialog(null, "File not found or not readable!", "ERROR", JOptionPane.WARNING_MESSAGE);
        }

    }

}
