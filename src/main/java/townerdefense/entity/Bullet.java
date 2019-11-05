package townerdefense.entity;

public class Bullet implements GameEntity {
    private double speed;
    private double strength;
    private double damage;

    public Bullet(double speed, double strength, double damage) {
        this.speed = speed;
        this.strength = strength;
        this.damage = damage;
    }
}
