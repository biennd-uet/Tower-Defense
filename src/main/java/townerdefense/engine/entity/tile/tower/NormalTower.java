package townerdefense.engine.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.bullet.Bullet;
import townerdefense.engine.entity.bullet.NormalBullet;

import java.util.Collection;

public class NormalTower extends Tower {
    private double frame_number = 0;
    private double x = 0;
    private double y = 0;
    private boolean reverse = false;

    private NormalTower(Image image, double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(image, posX, posY, width, height, speed, range, damage);
    }

    public NormalTower(int posX, int posY) {
        this(GameConfig.IMTower1, posX, posY, GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.TOWER_SPEED, GameConfig.TOWER_RANGE, GameConfig.TOWER_DAMAGE);
    }

    public NormalTower(double posX, double posY) {
        this(GameConfig.IMTower1, posX, posY, GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.TOWER_SPEED, GameConfig.TOWER_RANGE, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
    }

    @Override
    public Bullet spawn(int deltaTime) {


        double pX = this.getCenterPosX() - GameConfig.BULLET_WIDTH / 2.0;
        double pY = this.getCenterPosY() - GameConfig.BULLET_HEIGHT / 2.0;
        return new NormalBullet(enemyInRangeQueue.peek(), pX, pY, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public Collection<Bullet> spawnAll(int deltaTime) {
        return null;
    }
}
