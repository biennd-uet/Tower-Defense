package townerdefense.entity.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.GameConfig;
import townerdefense.entity.Entity;
import townerdefense.entity.UpdateableEntity;
import townerdefense.entity.enemy.Enemy;

public class Bullet extends Entity implements UpdateableEntity {
    public boolean isCanRemove = false;
    private double speed;
    private double damage;
    private Enemy enemy;

    public Bullet(Enemy enemy, double posX, double posY, double with, double height, double speed, double damage) {
        super(posX, posY, with, height);
        this.speed = speed;
        this.damage = damage;
        this.enemy = enemy;
    }

    public Bullet(Enemy enemy, double posX, double posY, double damage) {
        this(enemy, posX, posY,
                GameConfig.BULLET_WIDTH, GameConfig.BULLET_HEIGHT,
                GameConfig.BULLET_SPEED, damage);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.AQUA);
        graphicsContext.fillOval(this.getCenterPosX(), this.getCenterPosY(), width, height);
        //graphicsContext.strokeLine(this.posX, this.posY, this.enemy.getCenterPosX(), this.enemy.getCenterPosY());
    }

    @Override
    public void update(int deltaTime) {
        final double deltaDistance = this.speed * deltaTime / GameConfig.NPS;
        final double deltaX = enemy.getCenterPosX() - this.posX;
        final double deltaY = enemy.getCenterPosY() - this.posY;
        final double theta = Math.atan2(deltaY, deltaX);
        this.posX += deltaDistance * Math.cos(theta);
        this.posY += deltaDistance * Math.sin(theta);
    }
}
