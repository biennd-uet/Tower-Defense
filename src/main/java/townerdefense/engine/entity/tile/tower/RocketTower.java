package townerdefense.engine.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.Entity;
import townerdefense.engine.entity.bullet.Bullet;
import townerdefense.engine.entity.bullet.Roket;
import townerdefense.model.SoundManger;

import java.util.Collection;

public class RocketTower extends Tower {

    private RocketTower(double posX, double posY, double width, double height, double speed, double range, Collection<Entity> entities) {
        super(posX, posY, width, height, speed, range, entities);
    }

    public RocketTower(double posX, double posY, Collection<Entity> entities) {
        this(posX, posY,
                GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.ROCKET_TOWER_SPEED, GameConfig.ROCKET_TOWER_RANGE, entities);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
        graphicsContext.save();
        rotate(graphicsContext, theta, this.getCenterPosX(), this.getCenterPosY());
        graphicsContext.drawImage(GameConfig.IMRocketTower, posX, posY, width, height);
        graphicsContext.restore();
    }

    @Override
    public Bullet spawn(int deltaTime) {
        SoundManger.getInstance().playRocketSound();
        double pX = this.getCenterPosX() - GameConfig.ROCKET_WIDTH / 2.0;
        double pY = this.getCenterPosY() - GameConfig.ROCKET_HEIGHT / 2.0;
        return new Roket(enemyInRangeQueue.peek(), pX, pY, GameConfig.ROCKET_TOWER_DAMAGE);
    }

    @Override
    public Collection<? extends Entity> spawnAll(int deltaTime) {
        return null;
    }

}
