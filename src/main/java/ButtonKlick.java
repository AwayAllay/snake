import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class ButtonKlick {

    public ButtonKlick(String file) {
        playSound(file);
    }

    public void playSound(final String fileName) {
        try {

            File soundFile = new File(getClass().getResource(fileName).toURI());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float volume = -10.0f;
            control.setValue(volume);

            clip.start();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    clip.close();
                    cancel();
                }
            }, clip.getMicrosecondLength() / 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
