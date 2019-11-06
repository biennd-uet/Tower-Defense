package townerdefense.entity.tile.tower;

import townerdefense.entity.UpdatableEntity;
import townerdefense.entity.tile.Tile;

public abstract class Tower extends Tile implements UpdatableEntity {
    private double speed;
    private double range;
    private double damage;

    public Tower(double posX, double posY, double with, double height, double speed, double range, double damage) {
        super(posX, posY, with, height);
        this.speed = speed;
        this.range = range;
        this.damage = damage;
    }

    @Override
    public void update(int deltaTime) {

    }
}
