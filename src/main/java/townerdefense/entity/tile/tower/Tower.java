package townerdefense.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import townerdefense.GameConfig;
import townerdefense.GameField;
import townerdefense.entity.Entity;
import townerdefense.entity.SpawnableEntity;
import townerdefense.entity.UpdateableEntity;
import townerdefense.entity.bullet.Bullet;
import townerdefense.entity.enemy.Enemy;
import townerdefense.entity.other.Point;
import townerdefense.entity.tile.Tile;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Predicate;

public abstract class Tower extends Tile implements UpdateableEntity, SpawnableEntity {
    private final double timeBetweenTwoAttack;
    protected Queue<Enemy> enemyInRangeQueue;
    private double speed;
    private double range;
    private double damage;
    private Image image;
    private double lastTimeAttack;

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
    }

    //aim and attack
    @Override
    public Bullet spawn() {
        lastTimeAttack = System.nanoTime();
        return new Bullet(enemyInRangeQueue.peek(), this.getCenterPosX(), this.getCenterPosY(), this.damage);
    }

    private void findEnemyInRange() {
        Predicate<Entity> enemyInRange = entity -> Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                entity.getCenterPosX(), entity.getCenterPosY()) <= this.range;
        GameField.entities.stream()
                .filter(entity -> entity instanceof Enemy)
                .filter(enemy -> !enemyInRangeQueue.contains(enemy))
                .filter(enemyInRange)
                .forEach(enemy -> this.enemyInRangeQueue.add((Enemy) enemy));
    }

    private void removeEnemyOutRange() {
        Predicate<Entity> enemyOutRange = enemy -> Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                enemy.getCenterPosX(), enemy.getCenterPosY()) > this.range;
        this.enemyInRangeQueue.removeIf(enemyOutRange);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {

        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillOval(this.posX, this.posY,
                this.width, this.height);
        //(new Point(this.getCenterPosX(), getCenterPosY())).render(graphicsContext);
        if(this.enemyInRangeQueue.peek() != null) {
            graphicsContext.strokeLine(this.getCenterPosX(), this.getCenterPosY(),
                    enemyInRangeQueue.peek().getCenterPosX(), enemyInRangeQueue.peek().getCenterPosY());
        }
    }

    @Override
    public boolean hasEntityToSpawn() {
        return enemyInRangeQueue.size() > 0 && lastTimeAttack + timeBetweenTwoAttack <= System.nanoTime();
    }
}
