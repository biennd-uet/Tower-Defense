package townerdefense.engine.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.Entity;
import townerdefense.engine.entity.bullet.Bullet;
import townerdefense.engine.entity.bullet.NormalBullet;
import townerdefense.model.GameManager;
import townerdefense.model.SoundManger;

import java.util.Collection;

public class NormalTower extends Tower {
    private NormalTower(double posX, double posY, double width, double height, double speed, double range, Collection<Entity> entities) {
        super(posX, posY, width, height, speed, range, entities);
    }

    public NormalTower(double posX, double posY, Collection<Entity> entities) {
        this(posX, posY, GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.TOWER_SPEED, GameConfig.TOWER_RANGE, entities);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
        graphicsContext.save();
        rotate(graphicsContext, theta, this.getCenterPosX(), this.getCenterPosY());
        graphicsContext.drawImage(GameConfig.IMTower1, posX, posY, width, height);
        graphicsContext.restore();
    }

    @Override
    public Bullet spawn(int deltaTime) {
        SoundManger.getInstance().playGunSound();
        double pX = this.getCenterPosX() - GameConfig.BULLET_WIDTH / 2.0;
        double pY = this.getCenterPosY() - GameConfig.BULLET_HEIGHT / 2.0;
        return new NormalBullet(enemyInRangeQueue.peek(), pX, pY, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public Collection<Bullet> spawnAll(int deltaTime) {
        return null;
    }
}
