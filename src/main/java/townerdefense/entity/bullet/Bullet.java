package townerdefense.entity.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.GameConfig;
import townerdefense.GameField;
import townerdefense.entity.DestroyableEntity;
import townerdefense.entity.Entity;
import townerdefense.entity.UpdatableEntity;
import townerdefense.entity.enemy.Enemy;
import townerdefense.entity.other.Point;

public class Bullet extends Entity implements UpdatableEntity, DestroyableEntity {
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
        double deltaDistance = this.speed * deltaTime / GameConfig.NPS;
        double deltaX = enemy.getCenterPosX() - this.posX;
        double deltaY = enemy.getCenterPosY() - this.posY;
        double theta = Math.atan2(deltaY, deltaX);
        this.posX += deltaDistance * Math.cos(theta);
        this.posY += deltaDistance * Math.sin(theta);
    }

    @Override
    public boolean isDestroy() {
        return enemy.isDestroy() || Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                enemy.getCenterPosX(), this.getCenterPosY()) <= GameConfig.SIZE_UNIT;
    }

    @Override
    public void onDestroy() {
        enemy.onAttacked(this.damage);
    }
}
