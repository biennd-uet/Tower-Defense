package townerdefense.model;


import java.io.*;
import java.net.URISyntaxException;
import java.util.Set;

public class SettingManager implements Serializable {
    //Singleton Pattern
    private static SettingManager settingManager;
    private boolean hasMusic;
    private boolean hasSound;

    transient private MusicManager musicManager;

    private SettingManager() {
        hasSound = true;
        hasMusic = true;
        musicManager = MusicManager.getInstance();
    }

    public static SettingManager getInstance() {
        if (settingManager == null) {
            settingManager = new SettingManager();
        }
        return settingManager;
    }

    protected Object readResolve() {
        return SettingManager.getInstance();
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
        musicManager.stopMusic();
    }

    public void turnOnSound()  {
        this.hasSound = true;
    }

    public void turnOnMusic()  {
        this.hasMusic = true;
        musicManager.playMusic();
    }
}
