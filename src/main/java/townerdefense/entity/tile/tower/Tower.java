package townerdefense.entity.tile.tower;

import townerdefense.entity.tile.GameTile;

public abstract class Tower implements GameTile {
    private double speed;
    private double range;
    private double damage;

    public Tower(double speed, double range, double damage) {
        this.speed = speed;
        this.range = range;
        this.damage = damage;
    }
}
