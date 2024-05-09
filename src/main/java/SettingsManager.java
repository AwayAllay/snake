import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsManager {

    private static final String FILE_NAME = "snake.properties";

    /**
     * Loads all the saved settings from the user from the snake.properties file
     */
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

        //Configuration-stuff
        Skins skin = Skins.valueOf(properties.getProperty("skin"));
        Modes mode = Modes.valueOf(properties.getProperty("mode"));
        int unlockedLevel = Integer.parseInt(properties.getProperty("unlockedLevel"));
        long highestPoints = Integer.parseInt(properties.getProperty("highestPoints"));
        Levels highScoreLevel = Levels.valueOf(properties.getProperty("highScoreLevel"));
        String highScoreTime = properties.getProperty("highScoreTime");
        Modes highScoreMode = Modes.valueOf(properties.getProperty("highScoreMode"));


        if (highScoreMode != null)
            settings.setHighScoreMode(highScoreMode);
        if (highestPoints != 0)
            settings.setHighestPoints(highestPoints);
        if (highScoreLevel != null)
            settings.setHighScoreLevel(highScoreLevel);
        if (highScoreTime != null)
            settings.setHighScoreTime(highScoreTime);
        if (skin != null)
            settings.setSkin(skin);
        if (mode != null)
            settings.setMode(mode);
        if (unlockedLevel != 0)
            settings.setUnlockedLevel(unlockedLevel);

        //Achievements
        boolean NOOBcollected = Boolean.parseBoolean(properties.getProperty("NOOBcollected"));
        boolean WHATS_WINDING_THEREcollected = Boolean.parseBoolean(properties.getProperty("WHATS_WINDING_THEREcollected"));
        boolean BEGINNERcollected = Boolean.parseBoolean(properties.getProperty("BEGINNERcollected"));
        boolean GOT_THEM_ALLcollected = Boolean.parseBoolean(properties.getProperty("GOT_THEM_ALLcollected"));
        boolean SMELLS_FAMILIARcollected = Boolean.parseBoolean(properties.getProperty("SMELLS_FAMILIARcollected"));
        boolean SKIN_ENTHUSIASTcollected = Boolean.parseBoolean(properties.getProperty("SKIN_ENTHUSIASTcollected"));
        boolean YUMMYcollected = Boolean.parseBoolean(properties.getProperty("YUMMYcollected"));
        boolean IAM_GROWING_UPcollected = Boolean.parseBoolean(properties.getProperty("IAM_GROWING_UPcollected"));
        boolean PARENTcollected = Boolean.parseBoolean(properties.getProperty("PARENTcollected"));
        boolean MASTERcollected = Boolean.parseBoolean(properties.getProperty("MASTERcollected"));
        boolean DEMIGODcollected = Boolean.parseBoolean(properties.getProperty("DEMIGODcollected"));
        boolean SERPENT_GODcollected = Boolean.parseBoolean(properties.getProperty("SERPENT_GODcollected"));
        boolean DIVINITYcollected = Boolean.parseBoolean(properties.getProperty("DIVINITYcollected"));
        boolean THE_LONGEST_OF_THEM_ALLcollected = Boolean.parseBoolean(properties.getProperty("THE_LONGEST_OF_THEM_ALLcollected"));
        boolean COLLECTORcollected = Boolean.parseBoolean(properties.getProperty("COLLECTORcollected"));
        boolean COLLECTING_MASTERcollected = Boolean.parseBoolean(properties.getProperty("COLLECTING_MASTERcollected"));
        boolean COLLECTING_GODcollected = Boolean.parseBoolean(properties.getProperty("COLLECTING_GODcollected"));
        boolean COLLECTING_ADDICTcollected = Boolean.parseBoolean(properties.getProperty("COLLECTING_ADDICTcollected"));
        boolean I_HAVE_NO_LIFEcollected = Boolean.parseBoolean(properties.getProperty("I_HAVE_NO_LIFEcollected"));
        boolean GETTING_BETTERcollected = Boolean.parseBoolean(properties.getProperty("GETTING_BETTERcollected"));
        boolean EXPLOITcollected = Boolean.parseBoolean(properties.getProperty("EXPLOITcollected"));

        settings.setNOOBcollected(NOOBcollected);
        settings.setWHATS_WINDING_THEREcollected(WHATS_WINDING_THEREcollected);
        settings.setBEGINNERcollected(BEGINNERcollected);
        settings.setGOT_THEM_ALLcollected(GOT_THEM_ALLcollected);
        settings.setSMELLS_FAMILIARcollected(SMELLS_FAMILIARcollected);
        settings.setSKIN_ENTHUSIASTcollected(SKIN_ENTHUSIASTcollected);
        settings.setYUMMYcollected(YUMMYcollected);
        settings.setIAM_GROWING_UPcollected(IAM_GROWING_UPcollected);
        settings.setPARENTcollected(PARENTcollected);
        settings.setMASTERcollected(MASTERcollected);
        settings.setDEMIGODcollected(DEMIGODcollected);
        settings.setSERPENT_GODcollected(SERPENT_GODcollected);
        settings.setDIVINITYcollected(DIVINITYcollected);
        settings.setTHE_LONGEST_OF_THEM_ALLcollected(THE_LONGEST_OF_THEM_ALLcollected);
        settings.setCOLLECTORcollected(COLLECTORcollected);
        settings.setCOLLECTING_MASTERcollected(COLLECTING_MASTERcollected);
        settings.setCOLLECTING_GODcollected(COLLECTING_GODcollected);
        settings.setCOLLECTING_ADDICTcollected(COLLECTING_ADDICTcollected);
        settings.setI_HAVE_NO_LIFEcollected(I_HAVE_NO_LIFEcollected);
        settings.setGETTING_BETTERcollected(GETTING_BETTERcollected);
        settings.setEXPLOITcollected(EXPLOITcollected);




        return settings;
    }

    /**
     * Saves all the settings from the user to the snake.properties file
     */
    public void save(final Settings pSettings) {

        Properties properties = new Properties();

        //Configuration-stuff
        properties.setProperty("skin", String.valueOf(pSettings.getSkin()));
        properties.setProperty("mode", String.valueOf(pSettings.getMode()));
        properties.setProperty("unlockedLevel", String.valueOf(pSettings.getUnlockedLevel()));
        properties.setProperty("highestPoints", String.valueOf(pSettings.getHighestPoints()));
        properties.setProperty("highScoreLevel", String.valueOf(pSettings.getHighScoreLevel()));
        properties.setProperty("highScoreTime", pSettings.getHighScoreTime());
        properties.setProperty("highScoreMode", String.valueOf(pSettings.getHighScoreMode()));

        //Achievements
        properties.setProperty("NOOBcollected", String.valueOf(pSettings.isNOOBcollected()));
        properties.setProperty("WHATS_WINDING_THEREcollected", String.valueOf(pSettings.isWHATS_WINDING_THEREcollected()));
        properties.setProperty("BEGINNERcollected", String.valueOf(pSettings.isBEGINNERcollected()));
        properties.setProperty("GOT_THEM_ALLcollected", String.valueOf(pSettings.isGOT_THEM_ALLcollected()));
        properties.setProperty("SMELLS_FAMILIARcollected", String.valueOf(pSettings.isSMELLS_FAMILIARcollected()));
        properties.setProperty("SKIN_ENTHUSIASTcollected", String.valueOf(pSettings.isSKIN_ENTHUSIASTcollected()));
        properties.setProperty("YUMMYcollected", String.valueOf(pSettings.isYUMMYcollected()));
        properties.setProperty("IAM_GROWING_UPcollected", String.valueOf(pSettings.isIAM_GROWING_UPcollected()));
        properties.setProperty("PARENTcollected", String.valueOf(pSettings.isPARENTcollected()));
        properties.setProperty("MASTERcollected", String.valueOf(pSettings.isMASTERcollected()));
        properties.setProperty("DEMIGODcollected", String.valueOf(pSettings.isDEMIGODcollected()));
        properties.setProperty("SERPENT_GODcollected", String.valueOf(pSettings.isSERPENT_GODcollected()));
        properties.setProperty("DIVINITYcollected", String.valueOf(pSettings.isDIVINITYcollected()));
        properties.setProperty("THE_LONGEST_OF_THEM_ALLcollected", String.valueOf(pSettings.isTHE_LONGEST_OF_THEM_ALLcollected()));
        properties.setProperty("COLLECTORcollected", String.valueOf(pSettings.isCOLLECTORcollected()));
        properties.setProperty("COLLECTING_MASTERcollected", String.valueOf(pSettings.isCOLLECTING_MASTERcollected()));
        properties.setProperty("COLLECTING_GODcollected", String.valueOf(pSettings.isCOLLECTING_GODcollected()));
        properties.setProperty("COLLECTING_ADDICTcollected", String.valueOf(pSettings.isCOLLECTING_ADDICTcollected()));
        properties.setProperty("I_HAVE_NO_LIFEcollected", String.valueOf(pSettings.isI_HAVE_NO_LIFEcollected()));
        properties.setProperty("GETTING_BETTERcollected", String.valueOf(pSettings.isGETTING_BETTERcollected()));
        properties.setProperty("EXPLOITcollected", String.valueOf(pSettings.isEXPLOITcollected()));




        try {
            properties.store(new FileOutputStream(FILE_NAME), "The set settings of the user.");
        } catch (IOException e) {
            System.out.println("Sth went wrong");
            JOptionPane.showMessageDialog(null, "File not found or not readable!", "ERROR", JOptionPane.WARNING_MESSAGE);
        }

    }

}
