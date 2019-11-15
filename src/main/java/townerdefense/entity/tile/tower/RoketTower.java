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
import townerdefense.entity.bullet.Roket;
import townerdefense.entity.tile.SpriteAnimation;

public class RoketTower extends Tower {
    private double frame_number = 0;
    private double x = 0;
    private double y = 0;
    private boolean reverse = false;

    public RoketTower(Image image, double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(image, posX, posY, width, height, speed, range, damage);
    }

    //Default
    public RoketTower() {
        this(GameConfig.IMRoketTower, GameConfig.SIZE_UNIT*6 ,GameConfig.SIZE_UNIT*4 ,
                GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.ROCKET_TOWER_SPEED, GameConfig.ROCKET_TOWER_RANGE, GameConfig.ROCKET_TOWER_DAMAGE);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
    }

    @Override
    public Bullet spawn() {
        lastTimeAttack = System.nanoTime();
        double pX = this.getCenterPosX() - GameConfig.ROCKET_WIDTH/2;
        double pY = this.getCenterPosY() - GameConfig.ROCKET_HEIGHT/2;
        return new Roket(enemyInRangeQueue.peek(),pX,pY,GameConfig.ROCKET_TOWER_DAMAGE);
    }

}
