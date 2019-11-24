package townerdefense.engine.entity.tile.tower;

import javafx.scene.image.Image;
import townerdefense.engine.GameConfig;

public enum TypeOfTower {
    NormalTower(GameConfig.IMTower1, GameConfig.TOWER_RANGE, GameConfig.TOWER_PRICE),
    RocketTower(GameConfig.IMRocketTower, GameConfig.ROCKET_TOWER_RANGE, GameConfig.ROCKET_TOWER_PRICE),
    BeamTower(GameConfig.IMBeamTower, GameConfig.BEAM_TOWER_RANGE, GameConfig.BEAM_TOWER_PRICE),
    MachineGunTower(GameConfig.IMMachineGunTower, GameConfig.MACHINE_GUN_TOWER_RANGE, GameConfig.MACHINE_GUN_TOWER_PRICE);

    private Image image;
    private double range;
    private int price;

    TypeOfTower(Image image, double range, int price) {
        this.image = image;
        this.range = range;
        this.price = price;
    }

    public Image getImage() {
        return image;
    }

    public double getRange() {
        return range;
    }

    public int getPrice() {
        return price;
    }
}
