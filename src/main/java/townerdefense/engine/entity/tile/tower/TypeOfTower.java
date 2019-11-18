package townerdefense.engine.entity.tile.tower;

import javafx.scene.image.Image;
import townerdefense.engine.GameConfig;

public enum TypeOfTower {
    NormalTower(GameConfig.IMTower1, GameConfig.TOWER_RANGE),
    RocketTower(GameConfig.IMRocketTower, GameConfig.ROCKET_TOWER_RANGE),
    BeamTower(GameConfig.IMBeamTower, GameConfig.BEAM_TOWER_RANGE);

    private Image image;
    private double range;

    TypeOfTower(Image image, double range) {
        this.image = image;
        this.range = range;
    }

    public Image getImage() {
        return image;
    }

    public double getRange() {
        return range;
    }
}
