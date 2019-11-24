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

    private MachineGunTower(Image image, double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(image, posX, posY, width, height, speed, range, damage);
    }

    public MachineGunTower(double posX, double posY) {
        this(GameConfig.IMMachineGunTower, posX, posY,
                GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.MACHINE_GUN_TOWER_SPEED, GameConfig.MACHINE_GUN_TOWER_RANGE, GameConfig.MACHINE_GUN_TOWER_DAMAGE);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
    }

    @Override
    public Collection<Bullet> spawnAll(int deltaTime) {

        double pX1 = this.getCenterPosX();
        double pY1 = this.getCenterPosY();
        double pX2 = this.getCenterPosX() - GameConfig.BULLET_WIDTH / 2.0;
        double pY2 = this.getCenterPosY() - GameConfig.BULLET_HEIGHT / 2.0;
        double pX3 = this.getCenterPosX() - GameConfig.BULLET_WIDTH;
        double pY3 = this.getCenterPosY() - GameConfig.BULLET_HEIGHT;
        Collection<Bullet> bullets = new ArrayList<>();
        bullets.add(new NormalBullet(enemyInRangeQueue.peek(), pX1, pY1, GameConfig.TOWER_DAMAGE));
        bullets.add(new NormalBullet(enemyInRangeQueue.peek(), pX2, pY2, GameConfig.TOWER_DAMAGE));
        bullets.add(new NormalBullet(enemyInRangeQueue.peek(), pX3, pY3, GameConfig.TOWER_DAMAGE));
        return bullets;
    }

    @Override
    public Entity spawn(int deltaTime) {
        return new NormalBullet(enemyInRangeQueue.peek(), this.posX, this.posY, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public boolean hasEntityToSpawn(int deltaTime) {
        return false;
    }

    @Override
    public boolean hasEntitiesToSpawn(int deltaTime) {
        return super.hasEntityToSpawn(deltaTime);
    }
}
