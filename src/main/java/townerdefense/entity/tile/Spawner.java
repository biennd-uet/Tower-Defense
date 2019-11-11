package townerdefense.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.GameConfig;
import townerdefense.TypeOfEntity;
import townerdefense.entity.Entity;
import townerdefense.entity.SpawnableEntity;
import townerdefense.entity.UpdatableEntity;
import townerdefense.entity.enemy.Enemy;
import townerdefense.entity.enemy.NormalEnemy;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Spawner extends Tile implements UpdatableEntity, SpawnableEntity {
    private Queue<Enemy> enemies;
    private double lastTimeSpawn;
    private final double timeBetweenSpawnEnemy;

    public Spawner(double posX, double posY, double with, double height) {
        super(posX, posY, with, height);
        this.enemies = new ArrayDeque<>();
        lastTimeSpawn = 0;
        timeBetweenSpawnEnemy = GameConfig.NPS / GameConfig.SPAWNER_SPEED_SPAWN;
    }

    public Spawner() {
        this(GameConfig.SPAWNER_DEFAULT_POSX, GameConfig.SPAWNER_DEFAULT_POSY,
                GameConfig.SPAWNER_WIDTH, GameConfig.SPAWNER_HEIGHT);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.LIGHTGREEN);
        graphicsContext.fillOval(this.posX, this.posY, this.width, this.height);
        //System.out.printf("%f %f %f %f\n", this.posX, this.posY, this.with, this.height);
    }

    @Override
    public void update(int deltaTime) {

    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    @Override
    public Enemy spawn() {
        lastTimeSpawn = System.nanoTime();
        return enemies.remove();
    }

    @Override
    public boolean hasEntityToSpawn() {
        return !enemies.isEmpty() && timeBetweenSpawnEnemy + lastTimeSpawn <= System.nanoTime();
    }
}
