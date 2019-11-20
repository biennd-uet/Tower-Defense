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

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Predicate;

public abstract class Tower extends Tile implements UpdatableEntity, SpawnableEntity {
    private final double timeBetweenTwoAttack;
    protected Queue<Enemy> enemyInRangeQueue;
    protected double lastTimeAttack;
    protected double theta;
    private double speed;
    private double range;
    private double damage;
    private Image image;


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
        lastTimeAttack = lastTimeAttack + deltaTime;
        this.removeEnemyOutRange();
        this.findEnemyInRange();
        if (!enemyInRangeQueue.isEmpty()) {
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

    public void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(GameConfig.IMBlank, posX, posY, width, height);
        graphicsContext.save();
        rotate(graphicsContext, theta, this.getCenterPosX(), this.getCenterPosY());
        graphicsContext.drawImage(image, posX, posY, width, height);
        //graphicsContext.strokeOval(this.getCenterPosX()  - this.range / 2, this.getCenterPosY() - this.range / 2, this.range, this.range);
        graphicsContext.restore();
    }

    @Override
    public boolean hasEntityToSpawn(int deltaTime) {
        //System.out.println(lastTimeAttack);
        if(lastTimeAttack >= timeBetweenTwoAttack){
            lastTimeAttack = 0;
            return enemyInRangeQueue.size() > 0;
        }else return false;



    }

    @Override
    public boolean hasEntitiesToSpawn(int deltaTime) {
        return false;
    }
}
