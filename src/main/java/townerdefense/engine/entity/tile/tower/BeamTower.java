package townerdefense.engine.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.bullet.Bullet;
import townerdefense.engine.entity.bullet.Laze;
import townerdefense.engine.entity.bullet.NormalBullet;
import townerdefense.engine.entity.other.MediaManager;

import java.util.Collection;

public class BeamTower extends Tower {
    private double frame_number = 0;
    private double x = 0;
    private double y = 0;
    private boolean reverse = false;

    private BeamTower(Image image, double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(image, posX, posY, width, height, speed, range, damage);
    }

    public BeamTower(int posX, int posY) {
        this(GameConfig.IMBeamTower, posX, posY, GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.BEAM_TOWER_SPEED, GameConfig.BEAM_TOWER_RANGE, GameConfig.BEAM_TOWER_DAMAGE);
    }

    public BeamTower(double posX, double posY) {
        this(GameConfig.IMBeamTower, posX, posY, GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.BEAM_TOWER_SPEED, GameConfig.BEAM_TOWER_RANGE, GameConfig.BEAM_TOWER_DAMAGE);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
    }

    @Override
    public Bullet spawn(int deltaTime) {


        double pX = this.getCenterPosX() - GameConfig.BULLET_WIDTH*3 / 2.0;
        double pY = this.getCenterPosY() - GameConfig.BULLET_HEIGHT*3 / 2.0;
        return new Laze(enemyInRangeQueue.peek(), pX, pY, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public Collection<Bullet> spawnAll(int deltaTime) {
        return null;
    }
}
