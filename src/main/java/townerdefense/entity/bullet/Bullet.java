package townerdefense.entity.bullet;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.entity.Entity;
import townerdefense.entity.UpdatableEntity;

public class Bullet extends Entity implements UpdatableEntity {
    private double speed;
    private double strength;
    private double damage;

    public Bullet(double posX, double posY, double with, double height, double speed, double strength, double damage) {
        super(posX, posY, with, height);
        this.speed = speed;
        this.strength = strength;
        this.damage = damage;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {

    }

    @Override
    public void update(int tick) {

    }
}
