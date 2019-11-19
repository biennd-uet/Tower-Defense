package townerdefense.model;


public class Setting {
    //Singleton Pattern
    private static Setting setting;
    private boolean hasMusic;
    private boolean hasSound;

    private Setting() {
        hasSound = true;
        hasMusic = true;
    }

    public static Setting getInstance() {
        if (setting == null) {
            setting = new Setting();
        }
        return setting;
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
