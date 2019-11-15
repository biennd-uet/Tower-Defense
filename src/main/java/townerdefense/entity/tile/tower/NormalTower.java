package townerdefense.entity.tile.tower;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import townerdefense.GameConfig;
import townerdefense.entity.Entity;
import townerdefense.entity.bullet.Bullet;
import townerdefense.entity.bullet.NormalBullet;
import townerdefense.entity.tile.SpriteAnimation;

public class NormalTower extends Tower {
    private double frame_number = 0;
    private double x = 0;
    private double y = 0;
    private boolean reverse = false;

    public NormalTower(Image image, double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(image, posX, posY, width, height, speed, range, damage);
    }

    //Default
    public NormalTower() {
        this(GameConfig.IMTower1, GameConfig.TOWER_DEFAULT_POSX, GameConfig.TOWER_DEFAULT_POSY,
                GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.TOWER_SPEED, GameConfig.TOWER_RANGE, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
    }

    @Override
    public Bullet spawn() {
        lastTimeAttack = System.nanoTime();
        double pX = this.getCenterPosX() - GameConfig.BULLET_WIDTH/2;
        double pY = this.getCenterPosY() - GameConfig.BULLET_HEIGHT/2;
        return new NormalBullet(enemyInRangeQueue.peek(),pX,pY,GameConfig.TOWER_DAMAGE);
    }

}
