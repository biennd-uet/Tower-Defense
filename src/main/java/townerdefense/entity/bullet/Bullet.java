package townerdefense.entity.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import townerdefense.GameConfig;
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
    private double apha;
    private Image image;

    public Bullet(Enemy enemy,Image image, double posX, double posY, double with, double height, double speed, double damage) {
        super(posX, posY, with, height);
        this.speed = speed;
        this.damage = damage;
        this.enemy = enemy;
        this.image =image;
    }

    public Bullet(Enemy enemy, double posX, double posY, double damage) {
        this(enemy,GameConfig.IMBullet, posX, posY,
                GameConfig.BULLET_WIDTH, GameConfig.BULLET_HEIGHT,
                GameConfig.BULLET_SPEED, damage);
    }
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
    @Override
    public void render(GraphicsContext graphicsContext) {
     //   graphicsContext.setFill(Color.AQUA);
     //   graphicsContext.fillOval(this.getCenterPosX(), this.getCenterPosY(), width, height);
        graphicsContext.save();
        rotate(graphicsContext, apha, posX + width / 2, posY + height / 2);
        graphicsContext.drawImage(image, posX, posY, width, height);

        graphicsContext.restore();
    }

    @Override
    public void update(int deltaTime) {
        double deltaDistance = this.speed * deltaTime / GameConfig.NPS;
        double deltaX = enemy.getCenterPosX() - this.posX;
        double deltaY = enemy.getCenterPosY() - this.posY;
        double theta = Math.atan2(deltaY, deltaX);
        apha = Math.toDegrees(Math.PI - theta);
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
