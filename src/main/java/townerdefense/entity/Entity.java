package townerdefense.entity;

public abstract class Entity implements GameEntity, DrawableEntity {
    protected double posX;
    protected double posY;
    protected double with;
    protected double height;

    public Entity(double posX, double posY, double with, double height) {
        this.posX = posX;
        this.posY = posY;
        this.with = with;
        this.height = height;
    }
}
