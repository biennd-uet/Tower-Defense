package townerdefense.model;


public class Setting {
    private boolean hasMusic;
    private boolean hasSound;


    //Singleton Pattern
    private static Setting setting;
    public static Setting getInstance() {
        if (setting == null) {
            setting = new Setting();
        }
        return setting;
    }

    private Setting() {
        hasSound = true;
        hasMusic = true;
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
