package townerdefense.engine.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.SpawnableEntity;
import townerdefense.engine.entity.UpdatableEntity;
import townerdefense.engine.entity.bullet.Bullet;
import townerdefense.engine.entity.enemy.*;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

public class Spawner extends Tile implements UpdatableEntity, SpawnableEntity {
    private final double timeBetweenSpawnEnemy;
    private final double timeBetween2WayEnemy;
    private Queue<Enemy> enemies;
    private double lastTimeSpawn;
    private double lastTimeWayspaw;
    private double NEnemy = 5;
    private int NNormalEnemy;
    private int NTankEnemy;
    private int NPlane;
    private int NBossEnemy;
    private int dem;


    public Spawner(double posX, double posY, double with, double height) {
        super(posX, posY, with, height);
        this.enemies = new ArrayDeque<>();
        lastTimeSpawn = 0;
        lastTimeWayspaw = 0;
        timeBetweenSpawnEnemy = GameConfig.NPS / GameConfig.SPAWNER_SPEED_SPAWN;
        timeBetween2WayEnemy = GameConfig.NPS / 0.1;

    }

    public Spawner() {
        this(GameConfig.SPAWNER_DEFAULT_POSX, GameConfig.SPAWNER_DEFAULT_POSY,
                GameConfig.SPAWNER_WIDTH, GameConfig.SPAWNER_HEIGHT);
        Spaw1way();
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
    public void Spaw1way(){

        if(hasNewWayToSpawn()){
            //lastTimeWayspaw = System.nanoTime();
            NNormalEnemy = (int)(NEnemy*0.5);
            NTankEnemy = (int)(NEnemy*0.3);
            NPlane = (int)(NEnemy*0.1);
            NBossEnemy = (int)(NEnemy*0.1);
            for(int i=0;i<NNormalEnemy;i++){
                addEnemy(new NormalEnemy());
            }
            for(int i=0;i<NTankEnemy;i++){
                addEnemy(new TankEnemy());
            }
            for(int i=0;i<NPlane;i++){
                addEnemy(new Plane());
            }
            for(int i=0;i<NBossEnemy;i++){
                addEnemy(new BossEnemy());
            }
            NEnemy = NEnemy + 0.8;
            System.out.println(enemies.size());
        }

    }
    @Override
    public boolean hasEntityToSpawn() {
        hasNewWayToSpawn();
        Spaw1way();
        return !enemies.isEmpty() && timeBetweenSpawnEnemy + lastTimeSpawn <= System.nanoTime();
    }
    public boolean hasNewWayToSpawn() {
        if(!enemies.isEmpty())lastTimeWayspaw = System.nanoTime();
        return enemies.isEmpty() && timeBetween2WayEnemy + lastTimeWayspaw <= System.nanoTime();
    }
    @Override
    public Collection<Bullet> spawnAll() {
        return null;
    }
}
