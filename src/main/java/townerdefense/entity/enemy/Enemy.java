package townerdefense.entity.enemy;

import townerdefense.entity.GameEntity;

public abstract class Enemy implements GameEntity {
    private double health;
    private double speed;
    private double armor;
    private double reward;

    protected Enemy(double health, double speed, double armor, double reward) {
        this.health = health;
        this.speed = speed;
        this.armor = armor;
        this.reward = reward;
    }

}
