import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Playsound {
   private final String file;

    public Playsound(String file) {
        this.file = file;
    }

    public void playSound(){
        try {
            File sound = new File(getClass().getResource(file).toURI());
            Clip c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(sound));
            c.start();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
