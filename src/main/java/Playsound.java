import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Objects;

public class Playsound {
    private final String file;
    private Clip c;

    public Playsound(String file) {
        this.file = file;
    }

    public void playSound() {


        try (
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
                BufferedInputStream bis = new BufferedInputStream(Objects.requireNonNull(inputStream))
        ) {
            c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(bis));

            if (file.equalsIgnoreCase("Launcher.wav") || file.equalsIgnoreCase("GameBackground.wav")) {

                FloatControl floatControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
                //0.0f Leise 1.0f laut
                float volume = 0.05f;
                float db = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
                floatControl.setValue(db);

            }
            c.start();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void stop() {

        System.out.println(c);

        if (c != null) {
            System.out.println("works");
            c.close();
            c.stop();
        }
    }
}
