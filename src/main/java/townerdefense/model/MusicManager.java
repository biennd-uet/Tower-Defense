package townerdefense.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class MusicManager {
    private static MusicManager musicManager;

    private static Media media;

    static {
        URL musicURL = MusicManager.class.getResource("/music/RenaiCirculation.mp3");
        media = new Media(musicURL.toExternalForm());
    }

    private MediaPlayer mediaPlayer;

    private MusicManager() {
        mediaPlayer = new MediaPlayer(media);
    }

    public static MusicManager getInstance() {
        if (musicManager == null) {
            musicManager = new MusicManager();
        }
        return musicManager;
    }

    public void playMusic() {
        mediaPlayer.play();
    }

    public void stopMusic() {
        mediaPlayer.stop();
    }
}
