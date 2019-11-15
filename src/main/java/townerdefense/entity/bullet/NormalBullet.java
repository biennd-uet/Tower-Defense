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

public class NormalBullet extends Bullet {
    public boolean isCanRemove = false;
    private double speed;
    private double damage;
    private Enemy enemy;
    private double apha;
    private Image image;

    public NormalBullet(Enemy enemy,Image image, double posX, double posY, double with, double height, double speed, double damage) {
       super(enemy,image, posX, posY,
               with, height,
               speed, damage);
    }
    public NormalBullet(Enemy enemy, double posX, double posY, double damage){
        this(enemy,GameConfig.IMBullet, posX, posY,
                GameConfig.BULLET_WIDTH, GameConfig.BULLET_HEIGHT,
                GameConfig.BULLET_SPEED, damage);
    }
    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
    }

    @Override
    public void update(int deltaTime) {
       super.update(deltaTime);
    }

    @Override
    public boolean isDestroy() {
       /* return enemy.isDestroy() || Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                enemy.getCenterPosX(), this.getCenterPosY()) <= GameConfig.SIZE_UNIT;*/
       return super.isDestroy();
    }

    @Override
    public void onDestroy() {
      //  enemy.onAttacked(this.damage);
        super.onDestroy();
    }
}
