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
            System.out.println("BLABLA");
            //TODO siehe unten DATEI NICHT LESBAR
        }



        Settings settings = new Settings();

        String skin = properties.getProperty("skin");
        String mode = properties.getProperty("mode");

        if (skin != null){
            settings.setSkin(skin);
        }
        if (mode != null){
            settings.setMode(mode);
        }

        return settings;
    }

    public void save(final Settings pSettings){

        Properties properties = new Properties();

        properties.setProperty("skin", pSettings.getSkin());
        properties.setProperty("mode", pSettings.getMode());

        try {
            properties.store(new FileOutputStream(FILE_NAME), "The set settings of the user.");
        } catch (IOException e) {
            System.out.println("Sth went wrong");
            //TODO Handel this
        }

    }

}
