package townerdefense.engine.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.Entity;
import townerdefense.engine.entity.bullet.Bullet;
import townerdefense.engine.entity.bullet.NormalBullet;
import townerdefense.engine.entity.enemy.Enemy;

import java.util.Collection;

public class BeamTower extends Tower {

    private BeamTower(double posX, double posY, double width, double height, double speed, double range, Collection<Entity> entities) {
        super(posX, posY, width, height, speed, range, entities);
    }

    public BeamTower(double posX, double posY, Collection<Entity> entities) {
        this(posX, posY,
                GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.BEAM_TOWER_SPEED, GameConfig.TOWER_RANGE, entities);
    }

    private boolean UP(Entity enemy) {
        return enemy.getCenterPosX() > posX && enemy.getCenterPosX() < (posX + width) && enemy.getCenterPosY() < getCenterPosY() - height / 2;
    }

    private boolean LEFT(Entity enemy) {
        return enemy.getCenterPosY() > posY && enemy.getCenterPosY() < (posY + height) && enemy.getCenterPosX() < getCenterPosX() - width / 2;
    }

    private boolean DOWN(Entity enemy) {
        return enemy.getCenterPosX() > posX && enemy.getCenterPosX() < (posX + width) && enemy.getCenterPosY() > getCenterPosY() + height / 2;
    }

    private boolean RIGHT(Entity enemy) {
        return enemy.getCenterPosY() > posY && enemy.getCenterPosY() < (posY + height) && enemy.getCenterPosX() > getCenterPosX() + width / 2;
    }
    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
        graphicsContext.save();
        rotate(graphicsContext, theta, this.getCenterPosX(), this.getCenterPosY());
        graphicsContext.drawImage(GameConfig.IMBeamTower, posX, posY, width, height);
        graphicsContext.restore();
    }

    @Override
    public void update(int deltaTime) {
        if (!enemyInRangeQueue.isEmpty()) {
            Enemy enemy = enemyInRangeQueue.peek();
            if (LEFT(enemy)) {
                theta = -90;
            } else if (RIGHT(enemy)) {
                theta = 90;
            } else if (DOWN(enemy)) {
                theta = 180;
            } else {
                theta = 0;
            }
        }
    }

    @Override
    public Bullet spawn(int deltaTime) {
        lastTimeAttack = System.nanoTime();
        return new NormalBullet(enemyInRangeQueue.peek(), posX, posY, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public Collection<? extends Entity> spawnAll(int deltaTime) {
        return null;
    }

}


