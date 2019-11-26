package townerdefense.engine.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import townerdefense.engine.GameConfig;
import townerdefense.engine.GameField;
import townerdefense.engine.entity.DestroyableEntity;
import townerdefense.engine.entity.Entity;
import townerdefense.engine.entity.SpawnableEntity;
import townerdefense.engine.entity.UpdatableEntity;
import townerdefense.engine.entity.enemy.Enemy;
import townerdefense.engine.entity.other.Point;
import townerdefense.engine.entity.tile.Tile;
import townerdefense.model.SoundManger;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Predicate;

public abstract class Tower extends Tile implements UpdatableEntity, SpawnableEntity {
    private final double timeBetweenTwoAttack;
    Queue<Enemy> enemyInRangeQueue;
    double lastTimeAttack;
    double theta;
    private double range;
    private Image image;
    protected double frame_number = 0;
    protected double x = 0;
    protected double y = 0;
    protected boolean reverse = false;


    public Tower(Image image, double posX, double posY, double width, double height, double speed, double range) {
        super(posX, posY, width, height);
        this.image = image;
        this.range = range;
        enemyInRangeQueue = new PriorityQueue<>(new Comparator<Enemy>() {
            @Override
            public int compare(Enemy o1, Enemy o2) {
                double distanceO1ToTower = Point.getDistance(o1.getCenterPosX(), o1.getCenterPosY(), getCenterPosX(), getCenterPosY());
                double distanceO2ToTower = Point.getDistance(o2.getCenterPosX(), o2.getCenterPosY(), getCenterPosX(), getCenterPosY());
                return Double.compare(distanceO1ToTower, distanceO2ToTower);
            }
        });
        this.lastTimeAttack = 0;
        timeBetweenTwoAttack = GameConfig.NPS / speed;
    }

    @Override
    public void update(int deltaTime) {
        lastTimeAttack = lastTimeAttack + deltaTime;
        this.removeEnemyOutRange();
        this.findEnemyInRange();
        if (!enemyInRangeQueue.isEmpty()) {
            double deltaX = enemyInRangeQueue.peek().getCenterPosX() - this.getCenterPosX();
            assert enemyInRangeQueue.peek() != null;
            double deltaY = enemyInRangeQueue.peek().getCenterPosY() - this.getCenterPosY();
            theta = Math.toDegrees(Math.PI - Math.atan2(deltaX, deltaY));
        }
    }

    private void findEnemyInRange() {
        Predicate<Entity> enemyInRange = entity -> Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                entity.getCenterPosX(), entity.getCenterPosY()) < this.range;
        GameField.entities.parallelStream()
                .filter(entity -> entity instanceof Enemy)
                .filter(enemy -> !enemyInRangeQueue.contains(enemy))
                .filter(enemyInRange)
                .forEach(enemy -> this.enemyInRangeQueue.add((Enemy) enemy));
    }

    private void removeEnemyOutRange() {
        Predicate<Entity> enemyOutRange = enemy -> ((DestroyableEntity) enemy).isDestroy() ||
                Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                        enemy.getCenterPosX(), enemy.getCenterPosY()) > this.range;
        this.enemyInRangeQueue.removeIf(enemyOutRange);
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(GameConfig.IMBlank, posX, posY, width, height);
        graphicsContext.save();
        rotate(graphicsContext, theta, this.getCenterPosX(), this.getCenterPosY());
        graphicsContext.drawImage(image, posX, posY, width, height);
        graphicsContext.restore();
    }

    @Override
    public boolean hasEntityToSpawn(int deltaTime) {
        //System.out.println(lastTimeAttack);
        if (lastTimeAttack >= timeBetweenTwoAttack) {
            lastTimeAttack = 0;
            return enemyInRangeQueue.size() > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasEntitiesToSpawn(int deltaTime) {
        return false;
    }

}
