package townerdefense.engine.entity;

import java.io.Serializable;

public abstract class Entity implements GameEntity, DrawableEntity, Serializable {
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

    public double getCenterPosX() {
        return this.posX + this.width / 2;
    }

    public double getCenterPosY() {
        return this.posY + this.height / 2;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
}
