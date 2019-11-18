package townerdefense.engine.entity.other;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MediaManager {
    public static final Media GUN_SHOT_FX = new Media(MediaManager.class.getResource("asset/ak47.mp3").toString());
    public static final Media FOOTSTEP_FX = new Media(MediaManager.class.getResource("/zapsplat_foley_footsteps_dirt_road_walking_23331.mp3").toString());

    public void playMusic(String url) {
        Media sound = null;
        try {
            sound = new Media(new File(url).toURI().toString());
            MediaPlayer mediaManager = new MediaPlayer(sound);
            mediaManager.setVolume(0.5);
            mediaManager.play();
        } catch (Exception e) {
            System.err.println("music " + e.toString());
        }
    }

    public void playMusic(Media media, boolean repeat) {
        MediaPlayer mediaManager = new MediaPlayer(media);
        mediaManager.setVolume(0.5);
        if (repeat) {
            mediaManager.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    mediaManager.seek(Duration.ZERO);
                }
            });
        }
        mediaManager.play();
    }

    public void playMusic(Media media) {
        playMusic(media, false);
    }
}
