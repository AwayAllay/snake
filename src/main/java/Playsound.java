import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

public class Playsound {
   private final String file;
   private Clip c;

    public Playsound(String file) {
        this.file = file;
    }

    public void playSound(){

        try (
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
                BufferedInputStream bis = new BufferedInputStream(inputStream)
        )
        {
            c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(bis));

            if (file.equalsIgnoreCase("Launcher.wav")){

                FloatControl floatControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
                //0.0f Leise 1.0f laut
                float volume = 0.1f;
                float db = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
                floatControl.setValue(db);

            }


            c.start();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void stop(){

        if (c != null && c.isActive()){
            c.stop();
        }

    }
}
