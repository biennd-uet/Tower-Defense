package townerdefense.entity.tile.tower;

import townerdefense.entity.UpdateableEntity;
import townerdefense.entity.tile.Tile;

public abstract class Tower extends Tile implements UpdateableEntity {
    private double speed;
    private double range;
    private double damage;

    public Tower(double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(posX, posY, width, height);
        this.speed = speed;
        this.range = range;
        this.damage = damage;
    }
}
