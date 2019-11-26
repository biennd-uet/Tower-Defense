package townerdefense.engine.entity.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.enemy.Enemy;

public class Laze extends Bullet {
    public boolean isCanRemove = false;
    private double speed;
    private double damage;
    private Enemy enemy;
    private double apha;
    private Image image;

    public Laze(Enemy enemy, Image image, double posX, double posY, double with, double height, double speed, double damage) {
        super(enemy, image, posX, posY,
                with, height,
                speed, damage);
    }

    public Laze(Enemy enemy, double posX, double posY, double damage) {
        this(enemy, GameConfig.IMBeam, posX, posY,
                GameConfig.BULLET_WIDTH*3, GameConfig.BULLET_HEIGHT*3,
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
