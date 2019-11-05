package townerdefense.entity.gametile.tower;

import townerdefense.entity.gametile.GameTile;

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
