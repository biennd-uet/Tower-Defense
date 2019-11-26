package townerdefense.engine.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.SpawnableEntity;
import townerdefense.engine.entity.UpdatableEntity;
import townerdefense.engine.entity.bullet.Bullet;
import townerdefense.engine.entity.enemy.*;
import townerdefense.engine.entity.tile.map.Map;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

public class Spawner extends Tile implements UpdatableEntity, SpawnableEntity {
    private final double timeBetweenSpawnEnemy;
    private final double timeBetween2WayEnemy;
    private final Map map;
    private Queue<Enemy> enemies;
    private double lastTimeSpawn;
    private double lastTimeWayspaw;
    private double NEnemy = 5;
    private int NStage = 0;


    private Spawner(double posX, double posY, double with, double height, Map map) {
        super(posX, posY, with, height);
        this.enemies = new ArrayDeque<>();
        lastTimeSpawn = 0;
        lastTimeWayspaw = 0;
        timeBetweenSpawnEnemy = GameConfig.NPS / GameConfig.SPAWNER_SPEED_SPAWN;
        timeBetween2WayEnemy = GameConfig.NPS / 0.1;
        this.map = map;
    }

    public Spawner(Map map) {
        this(GameConfig.SPAWNER_DEFAULT_POSX, GameConfig.SPAWNER_DEFAULT_POSY,
                GameConfig.SPAWNER_WIDTH, GameConfig.SPAWNER_HEIGHT, map);
        SpawnOneWay();
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.LIGHTGREEN);
        graphicsContext.fillOval(this.posX, this.posY, this.width, this.height);
    }

    @Override
    public void update(int deltaTime) {
        lastTimeSpawn = lastTimeSpawn + deltaTime;

        if (enemies.isEmpty()) {
            lastTimeWayspaw = lastTimeWayspaw + deltaTime;
            if (lastTimeWayspaw >= timeBetween2WayEnemy) {
                lastTimeWayspaw = 0;
                SpawnOneWay();
            }
        }
    }

    private void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    @Override
    public Enemy spawn(int deltaTime) {
        return enemies.remove();
    }

    private void SpawnOneWay() {
        int NNormalEnemy = (int) (NEnemy * 0.5);
        int NTankEnemy = (int) (NEnemy * 0.3);
        int NPlane = (int) (NEnemy * 0.1);
        int NBossEnemy = (int) (NEnemy * 0.1);
        for (int i = 0; i < NNormalEnemy; i++) {
            addEnemy(new NormalEnemy(this.getPosX(), this.getPosY(), map));
        }
        for (int i = 0; i < NTankEnemy; i++) {
            addEnemy(new TankEnemy(this.getPosX(), this.getPosY(), map));
        }
        for (int i = 0; i < NPlane; i++) {
            addEnemy(new Plane(this.getPosX(), this.getPosY(), map));
        }
        for (int i = 0; i < NBossEnemy; i++) {
            addEnemy(new BossEnemy(this.getPosX(), this.getPosY(), map));
        }
        NEnemy = NEnemy + 0.8;
        NStage++;
        System.out.println(enemies.size());
    }

    @Override
    public boolean hasEntityToSpawn(int deltaTime) {
        if (lastTimeSpawn >= timeBetweenSpawnEnemy) {
            lastTimeSpawn = 0;
            return !enemies.isEmpty();
        } else {
            return false;
        }
    }

    @Override
    public boolean hasEntitiesToSpawn(int deltaTime) {
        return false;
    }

    @Override
    public Collection<Bullet> spawnAll(int deltaTime) {
        return null;
    }

    public int getNStage() {
        return NStage;
    }
}
