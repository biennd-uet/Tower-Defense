package townerdefense.entity.enemy;

import townerdefense.GameConfig;
import townerdefense.entity.UpdateableEntity;
import townerdefense.entity.tile.Tile;

public abstract class Enemy extends Tile implements UpdateableEntity {
    protected double health;
    protected double speed;
    protected double armor;
    protected double reward;

    protected Enemy(double posX, double posY, double width, double height, double health, double speed, double armor, double reward) {
        super(posX, posY, width, height);
        this.health = health;
        this.speed = speed;
        this.armor = armor;
        this.reward = reward;
    }

    @Override
    public void update(int deltaTime) {
        this.posY -= this.speed * deltaTime / GameConfig.NPS;
    }
}
