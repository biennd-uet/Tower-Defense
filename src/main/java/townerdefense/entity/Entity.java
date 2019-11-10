package townerdefense.entity;

import javafx.scene.image.Image;
import townerdefense.GameConfig;

public abstract class Entity implements GameEntity, DrawableEntity {
    protected double posX;
    protected double posY;
    protected double width;
    protected double height;
    private Image image;

    public Entity(double posX, double posY, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;

    }

    protected double getCenterPosX() {
        return this.posX + (GameConfig.SIZE_TILE_WIDTH - this.width) / 2;
    }

    protected double getCenterPosY() {
        return this.posY + (GameConfig.SIZE_TILE_HEIGHT - this.height) / 2;
    }
}
