package townerdefense.engine.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.Entity;
import townerdefense.engine.entity.bullet.Bullet;
import townerdefense.engine.entity.bullet.Laze;

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

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
        graphicsContext.save();
        rotate(graphicsContext, theta, this.getCenterPosX(), this.getCenterPosY());
        graphicsContext.drawImage(GameConfig.IMBeamTower, posX, posY, width, height);
        graphicsContext.restore();
    }

    @Override
    public Bullet spawn(int deltaTime) {
        double pX = this.getCenterPosX() - GameConfig.BULLET_WIDTH * 3 / 2.0;
        double pY = this.getCenterPosY() - GameConfig.BULLET_HEIGHT * 3 / 2.0;
        return new Laze(enemyInRangeQueue.peek(), pX, pY, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public Collection<Bullet> spawnAll(int deltaTime) {
        return null;
    }
}
