package townerdefense.entity.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.GameConfig;
import townerdefense.entity.DestroyableEntity;
import townerdefense.entity.Entity;
import townerdefense.entity.UpdateableEntity;
import townerdefense.entity.enemy.Enemy;

public class Bullet extends Entity implements UpdateableEntity, DestroyableEntity {
    public boolean isCanRemove = false;
    private double speed;
    private double damage;
    private Enemy enemy;
    private double deltaDistance;
    private double deltaX;
    private double deltaY;
    private double theta;
    private double Distance;

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
         deltaDistance = this.speed * deltaTime / GameConfig.NPS;
         deltaX = enemy.getCenterPosX() - this.posX;
         deltaY = enemy.getCenterPosY() - this.posY;
         theta = Math.atan2(deltaY, deltaX);
        this.posX += deltaDistance * Math.cos(theta);
        this.posY += deltaDistance * Math.sin(theta);
        Distance = Math.sqrt(Math.pow(enemy.getCenterPosX()-this.getCenterPosX(),2) + Math.pow(enemy.getCenterPosY()-this.getCenterPosY(),2) );
    }

    @Override
    public boolean onDestroy() {
        return this.Distance <= 10;
    }
}
