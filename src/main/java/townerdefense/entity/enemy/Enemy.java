package townerdefense.entity.enemy;

import townerdefense.entity.UpdatableEntity;
import townerdefense.entity.tile.Tile;

public abstract class Enemy extends Tile implements UpdatableEntity {
    private double health;
    private double speed;
    private double armor;
    private double reward;

    protected Enemy(double posX, double posY, double with, double height, double health, double speed, double armor, double reward) {
        super(posX, posY, with, height);
        this.health = health;
        this.speed = speed;
        this.armor = armor;
        this.reward = reward;
    }

    @Override
    public void update(int deltaTime) {

    }
}
