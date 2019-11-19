package townerdefense.engine.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.bullet.Bullet;
import townerdefense.engine.entity.bullet.Roket;

import java.util.Collection;

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
        this(GameConfig.IMRocketTower, GameConfig.SIZE_UNIT * 6, GameConfig.SIZE_UNIT * 5,
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
        double pX = this.getCenterPosX() - GameConfig.ROCKET_WIDTH / 2;
        double pY = this.getCenterPosY() - GameConfig.ROCKET_HEIGHT / 2;
        return new Roket(enemyInRangeQueue.peek(), pX, pY, GameConfig.ROCKET_TOWER_DAMAGE);
    }

    @Override
    public Collection<Bullet> spawnAll() {
        return null;
    }
}
