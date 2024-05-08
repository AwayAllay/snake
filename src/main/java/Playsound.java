import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Playsound {
   private final String file;
   private Clip c;

    public Playsound(String file) {
        this.file = file;
    }

    public void playSound(){
        try {
            File sound = new File(getClass().getResource(file).toURI());
            c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(sound));

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
