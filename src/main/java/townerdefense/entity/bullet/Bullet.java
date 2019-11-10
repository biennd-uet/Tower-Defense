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
    }

    @Override
    public void update(int deltaTime) {
        final double deltaDistance = speed * deltaTime / GameConfig.NPS;
        final double Distance = Math.sqrt(Math.pow(getCenterPosX() - enemy.getCenterPosX(), 2) + Math.pow(getCenterPosY() - enemy.getCenterPosY(), 2));
        final double cos = (Math.abs(getCenterPosX() - enemy.getCenterPosX())) / Distance;
        final double sin = 1 - Math.pow(cos, 2);
        final double deltaDistanceX = deltaDistance * cos;
        final double deltaDistanceY = deltaDistance * sin;
        if(getCenterPosX() < enemy.getCenterPosX() && getCenterPosY() > enemy.getCenterPosY()) {
            posX = + deltaDistanceX;
            posY = - deltaDistanceY;
        } else if(getCenterPosX() > enemy.getCenterPosX() && getCenterPosY() > enemy.getCenterPosY()) {
            posX = - deltaDistanceX;
            posY = - deltaDistanceY;
        } else if(getCenterPosX() > enemy.getCenterPosX() && getCenterPosY() < enemy.getCenterPosY()) {
            posX = - deltaDistanceX;
            posY = + deltaDistanceY;
        } else if(getCenterPosX() < enemy.getCenterPosX() && getCenterPosY() < enemy.getCenterPosY()) {
            posX = + deltaDistanceX;
            posY = + deltaDistanceY;
        }
        if(deltaDistance <= 10)
            isCanRemove = true;
    }
}
