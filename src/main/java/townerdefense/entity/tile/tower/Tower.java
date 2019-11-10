package townerdefense.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import townerdefense.GameField;
import townerdefense.entity.Entity;
import townerdefense.entity.UpdateableEntity;
import townerdefense.entity.enemy.Enemy;
import townerdefense.entity.other.Point;
import townerdefense.entity.tile.Tile;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Predicate;

public abstract class Tower extends Tile implements UpdateableEntity {
    protected Queue<Enemy> enemyQueue;
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
        enemyQueue = new ArrayDeque<>();
    }

    @Override
    public void update(int deltaTime) {
        this.removeEnemyOutRange();
        this.findEnemyInRange();
    }

    private void findEnemyInRange() {
        Predicate<Entity> enemyInRange = entity -> {
            if(entity instanceof Enemy && ! enemyQueue.contains(entity)) {
                if(Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                        entity.getCenterPosX(), entity.getCenterPosY()) <= this.range) {
                    return true;
                }
            }
            return false;
        };
        GameField.entities.stream().filter(enemyInRange).forEach(enemy -> this.enemyQueue.add((Enemy) enemy));
    }

    private void removeEnemyOutRange() {
        Predicate<Entity> enemyOutRange = enemy -> Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                enemy.getCenterPosX(), enemy.getCenterPosY()) > this.range;
        this.enemyQueue.removeIf(enemyOutRange);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillOval(this.posX, this.posY,
                this.width, this.height);
        (new Point(this.getCenterPosX(), getCenterPosY())).render(graphicsContext);
        if (this.enemyQueue.peek() != null) {
            assert enemyQueue.peek() != null;
            graphicsContext.strokeLine(this.getCenterPosX(), this.getCenterPosY(),
                    enemyQueue.peek().getCenterPosX(), enemyQueue.peek().getCenterPosY());
        }
    }
}
