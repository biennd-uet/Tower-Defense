package townerdefense.model.nonentity;

import townerdefense.engine.entity.DrawableEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class NonEntity implements DrawableEntity {

    public static List<NonEntity> nonEntities;

    static {
        nonEntities = new ArrayList<>();
    }

    protected final double width;
    protected final double height;
    protected double posX;
    protected double posY;

    NonEntity(double posX, double posY, double width, double height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
}

