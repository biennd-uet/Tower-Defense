package townerdefense.entity;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

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
