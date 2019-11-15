package townerdefense.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import townerdefense.GameConfig;
import townerdefense.GameField;
import townerdefense.entity.DestroyableEntity;
import townerdefense.entity.Entity;
import townerdefense.entity.SpawnableEntity;
import townerdefense.entity.UpdatableEntity;
import townerdefense.entity.bullet.Bullet;
import townerdefense.entity.enemy.Enemy;
import townerdefense.entity.other.Point;
import townerdefense.entity.tile.Tile;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Predicate;

public abstract class Tower extends Tile implements UpdatableEntity, SpawnableEntity {
    private final double timeBetweenTwoAttack;
    protected Queue<Enemy> enemyInRangeQueue;
    private double speed;
    private double range;
    private double damage;
    private Image image;
    protected double lastTimeAttack;
    protected double theta;


    public Tower(Image image, double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(posX, posY, width, height);
        this.image = image;
        this.speed = speed;
        this.range = range;
        this.damage = damage;
        enemyInRangeQueue = new ArrayDeque<>();
        this.lastTimeAttack = 0;
        timeBetweenTwoAttack = GameConfig.NPS / this.speed;
    }

    @Override
    public void update(int deltaTime) {
        this.removeEnemyOutRange();
        this.findEnemyInRange();
        if(! enemyInRangeQueue.isEmpty()) {
            double deltaX = enemyInRangeQueue.peek().getCenterPosX() - this.getCenterPosX();
            double deltaY = enemyInRangeQueue.peek().getCenterPosY() - this.getCenterPosY();
            theta = Math.toDegrees(Math.PI - Math.atan2(deltaX, deltaY));
        }
    }

    //aim and attack


    private void findEnemyInRange() {
        Predicate<Entity> enemyInRange = entity -> Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                entity.getCenterPosX(), entity.getCenterPosY()) <= this.range;
        GameField.entities.parallelStream()
                .filter(entity -> entity instanceof Enemy)
                .filter(enemy -> ! enemyInRangeQueue.contains(enemy))
                .filter(enemyInRange)
                .forEach(enemy -> this.enemyInRangeQueue.add((Enemy) enemy));
    }

    private void removeEnemyOutRange() {
        Predicate<Entity> enemyOutRange = enemy -> ((DestroyableEntity) enemy).isDestroy() ||
                Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                        enemy.getCenterPosX(), enemy.getCenterPosY()) > this.range;
        this.enemyInRangeQueue.removeIf(enemyOutRange);
    }

    public void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(GameConfig.IMBlank,posX,posY,width,height);
        graphicsContext.save();
        rotate(graphicsContext, theta, posX + width / 2, posY + height / 2);
        graphicsContext.drawImage(image, posX, posY, width, height);

        graphicsContext.restore();
    }

    @Override
    public boolean hasEntityToSpawn() {
        return enemyInRangeQueue.size() > 0 && lastTimeAttack + timeBetweenTwoAttack <= System.nanoTime() ;
    }
}
