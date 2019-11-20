package townerdefense.engine.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import townerdefense.engine.GameConfig;
import townerdefense.engine.GameField;
import townerdefense.engine.entity.DestroyableEntity;
import townerdefense.engine.entity.Entity;
import townerdefense.engine.entity.bullet.Bullet;
import townerdefense.engine.entity.bullet.Laze;
import townerdefense.engine.entity.enemy.Enemy;
import townerdefense.engine.entity.enemy.Plane;

import java.util.Collection;
import java.util.function.Predicate;

public class BeamTower extends Tower {
    private double frame_number = 0;
    private double x = 0;
    private double y = 0;
    private boolean reverse = false;

    public BeamTower(Image image, double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(image, posX, posY, width, height, speed, range, damage);
    }

    //Default
    public BeamTower() {
        this(GameConfig.IMBeamTower, GameConfig.SIZE_UNIT * 9, GameConfig.SIZE_UNIT * 4,
                GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.BEAM_TOWER_SPEED, GameConfig.TOWER_RANGE, GameConfig.TOWER_DAMAGE);
    }

    public BeamTower(double posX, double posY) {
        this(GameConfig.IMBeamTower, posX, posY,
                GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.BEAM_TOWER_SPEED, GameConfig.TOWER_RANGE, GameConfig.TOWER_DAMAGE);
    }

    private boolean UP(Entity enemy) {
        if (enemy.getCenterPosX() > posX && enemy.getCenterPosX() < (posX + width) && enemy.getCenterPosY() < getCenterPosY() - height / 2)
            return true;
        else return false;
    }

    private boolean LEFT(Entity enemy) {
        if (enemy.getCenterPosY() > posY && enemy.getCenterPosY() < (posY + height) && enemy.getCenterPosX() < getCenterPosX() - width / 2)
            return true;
        else return false;
    }

    private boolean DOWN(Entity enemy) {
        if (enemy.getCenterPosX() > posX && enemy.getCenterPosX() < (posX + width) && enemy.getCenterPosY() > getCenterPosY() + height / 2)
            return true;
        else return false;
    }

    private boolean RIGHT(Entity enemy) {
        if (enemy.getCenterPosY() > posY && enemy.getCenterPosY() < (posY + height) && enemy.getCenterPosX() > getCenterPosX() + width / 2)
            return true;
        else return false;
    }

    private void findEnemyInRange() {
        Predicate<Entity> enemyInRange = entity -> (UP(entity) || DOWN(entity) || LEFT(entity) || RIGHT(entity)) && !(entity instanceof Plane);
        GameField.entities.parallelStream()
                .filter(entity -> entity instanceof Enemy)
                .filter(enemy -> !enemyInRangeQueue.contains(enemy))
                .filter(enemyInRange)
                .forEach(enemy -> this.enemyInRangeQueue.add((Enemy) enemy));
    }

    private void removeEnemyOutRange() {
        Predicate<Entity> enemyOutRange = enemy -> ((DestroyableEntity) enemy).isDestroy() ||
                !(UP(enemy) && DOWN(enemy) && LEFT(enemy) && RIGHT(enemy));
        this.enemyInRangeQueue.removeIf(enemyOutRange);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
    }

    @Override
    public void update(int deltaTime) {
        //super.update(deltaTime);
        // this.findEnemyInRange();

        //   System.out.println(enemyInRangeQueue.size());
        if (!enemyInRangeQueue.isEmpty()) {
            if (LEFT(enemyInRangeQueue.peek())) {
                theta = -90;
            } else if (RIGHT(enemyInRangeQueue.peek())) {
                theta = 90;
            } else if (DOWN(enemyInRangeQueue.peek())) {
                theta = 180;
            } else {
                theta = 0;
            }
        }
        // this.removeEnemyOutRange();
    }

    @Override
    public Bullet spawn(int deltaTime) {
        lastTimeAttack = System.nanoTime();
        return new Laze(enemyInRangeQueue, posX, posY, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public Collection<? extends Entity> spawnAll(int deltaTime) {
        return null;
    }

}


