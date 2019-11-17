package townerdefense.engine.entity.tile.tower;

import javafx.scene.image.Image;
import townerdefense.engine.GameConfig;

public enum TypeOfTower {
    NORMAL_TOWER(GameConfig.IMTower1);
    private Image image;

    TypeOfTower(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
