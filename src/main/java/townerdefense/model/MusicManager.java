package townerdefense.model;

public class MusicManager {
    private static MusicManager musicManager;

    public static MusicManager getInstance() {
        if (musicManager == null) {
            musicManager = new MusicManager();
        }
        return musicManager;
    }

    private MusicManager() {

    }
}
