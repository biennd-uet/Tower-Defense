package townerdefense.engine.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.Entity;
import townerdefense.engine.entity.bullet.Bullet;
import townerdefense.engine.entity.bullet.NormalBullet;

import java.util.ArrayList;
import java.util.Collection;

public class MachineGunTower extends Tower {
    private double frame_number = 0;
    private double x = 0;
    private double y = 0;
    private boolean reverse = false;

    public MachineGunTower(Image image, double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(image, posX, posY, width, height, speed, range, damage);
    }

    //Default
    public MachineGunTower() {
        this(GameConfig.IMMachineGunTower, 4 * GameConfig.SIZE_TILE_WIDTH, 5 * GameConfig.SIZE_TILE_HEIGHT,
                GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.TOWER_SPEED, GameConfig.TOWER_RANGE, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
    }

    @Override
    public Collection<Bullet> spawnAll() {
        lastTimeAttack = System.nanoTime();
        double pX1 = this.getCenterPosX();
        double pY1 = this.getCenterPosY();
        double pX2 = this.getCenterPosX() - GameConfig.BULLET_WIDTH / 2;
        double pY2 = this.getCenterPosY() - GameConfig.BULLET_HEIGHT / 2;
        double pX3 = this.getCenterPosX() - 2 * GameConfig.BULLET_WIDTH / 2;
        double pY3 = this.getCenterPosY() - 2 * GameConfig.BULLET_HEIGHT / 2;
        Collection<Bullet> bullets = new ArrayList<>();
        bullets.add(new NormalBullet(enemyInRangeQueue.peek(), pX1, pY1, GameConfig.TOWER_DAMAGE));
        bullets.add(new NormalBullet(enemyInRangeQueue.peek(), pX2, pY2, GameConfig.TOWER_DAMAGE));
        bullets.add(new NormalBullet(enemyInRangeQueue.peek(), pX3, pY3, GameConfig.TOWER_DAMAGE));
        return bullets;
    }

    @Override
    public Entity spawn() {
        return new NormalBullet(enemyInRangeQueue.peek(), this.posX, this.posY, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public boolean hasEntityToSpawn() {
        return false;
    }

    @Override
    public boolean hasEntitiesToSpawn() {
        return super.hasEntityToSpawn();
    }
}
