package townerdefense.model;

public class SoundManger {
    private static SoundManger soundManger;

    public static SoundManger getInstance() {
        if (soundManger == null) {
            soundManger = new SoundManger();
        }
        return soundManger;
    }

    private SoundManger() {

    }
}
