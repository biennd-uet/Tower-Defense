package townerdefense.entity;

import townerdefense.GameConfig;

public abstract class Entity implements GameEntity, DrawableEntity {
    protected double posX;
    protected double posY;
    protected double width;
    protected double height;

    public Entity(double posX, double posY, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    @Override
    public double getRealPosX() {
        return this.posX + (GameConfig.SIZE_TILE_WIDTH - this.width) / 2;
    }

    @Override
    public double getRealPosY() {
        return this.posY + (GameConfig.SIZE_TILE_HEIGHT - this.height) / 2;
    }
}
