package townerdefense.model;


public class SettingManager {
    //Singleton Pattern
    private static SettingManager settingManager;
    private boolean hasMusic;
    private boolean hasSound;

    private SettingManager() {
        hasSound = true;
        hasMusic = true;
    }

    public static SettingManager getInstance() {
        if (settingManager == null) {
            settingManager = new SettingManager();
        }
        return settingManager;
    }

    public boolean hasSound() {
        return hasSound;
    }

    public boolean hasMusic() {
        return hasMusic;
    }

    public void turnOffSound() {
        this.hasSound = false;
    }

    public void turnOffMusic() {
        this.hasMusic = false;
    }

    public void turnOnSound() {
        this.hasSound = true;
    }

    public void turnOnMusic() {
        this.hasMusic = true;
    }
}
