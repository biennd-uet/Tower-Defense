package townerdefense.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.GameConfig;
import townerdefense.entity.SpawnableEntity;
import townerdefense.entity.UpdatableEntity;
import townerdefense.entity.enemy.Enemy;

import java.util.ArrayDeque;
import java.util.Queue;

public class Spawner extends Tile implements UpdatableEntity, SpawnableEntity {
    private final double timeBetweenSpawnEnemy;
    private Queue<Enemy> enemies;
    private double lastTimeSpawn;

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
        return ! enemies.isEmpty() && timeBetweenSpawnEnemy + lastTimeSpawn <= System.nanoTime();
    }
}
