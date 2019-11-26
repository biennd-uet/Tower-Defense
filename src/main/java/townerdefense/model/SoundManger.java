package townerdefense.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

public class SoundManger {

    private static Media gunSound;
    private static Media rocketSound;
    private MediaPlayer gunPlayer;
    private MediaPlayer rocketPlayer;

    static {
        URL musicURL = MusicManager.class.getResource("/sound/normalGun.wav");
        gunSound = new Media(musicURL.toExternalForm());
        rocketSound = new Media(SoundManger.class.getResource("/sound/rocketSound.wav").toExternalForm());
    }
    private static SoundManger soundManger;

    public static SoundManger getInstance() {
        if (soundManger == null) {
            soundManger = new SoundManger();
        }
        return soundManger;
    }

    private SoundManger() {
        gunPlayer = new MediaPlayer(gunSound);
        rocketPlayer = new MediaPlayer(rocketSound);
    }

    public void playGunSound() {
        gunPlayer.seek(Duration.ZERO);
        gunPlayer.play();
    }

    public void playRocketSound() {
        rocketPlayer.seek(Duration.ZERO);
        rocketPlayer.play();
    }
}
