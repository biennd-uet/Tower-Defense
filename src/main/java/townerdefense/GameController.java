package townerdefense;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import townerdefense.entity.Entity;
import townerdefense.entity.enemy.BossEnemy;
import townerdefense.entity.enemy.NormalEnemy;
import townerdefense.entity.enemy.Plane;
import townerdefense.entity.enemy.TankEnemy;
import townerdefense.entity.other.Point;
import townerdefense.entity.tile.Spawner;
import townerdefense.entity.tile.Target;
import townerdefense.entity.tile.map.Map;
import townerdefense.entity.tile.map.WayPoint;
import townerdefense.entity.tile.tower.BeamTower;
import townerdefense.entity.tile.tower.NormalTower;
import townerdefense.entity.tile.tower.RoketTower;

import java.util.List;

public class GameController extends AnimationTimer {

    public static List<Point> points;
    private final GraphicsContext graphicsContext;
    private final GameField gameField;
    private final Map map;
    private final WayPoint wayPoint;
    private final Spawner spawner;
    private long lastTime;
    private long lag;


    public GameController(GraphicsContext graphicsContext, Pane pane) {
        this.graphicsContext = graphicsContext;
        //Todo: init Game field with GameStage and get return this
        //GameStage gameStage = new GameStage();
        //this.gameField = gameStage.getGameField()
        //Todo: comment this code after finish before code
        this.map = new Map();
        this.wayPoint = new WayPoint();
        this.spawner = new Spawner();
        points = wayPoint.getPoints();

        this.gameField = new GameField();

        this.gameField.addAllEntity(map.getListTile());
        //this.gameField.addEntity(this.wayPoint);
        this.gameField.addEntity(this.spawner);
        this.gameField.addEntity(new Target());
        this.gameField.addEntity(new NormalTower());
        this.gameField.addEntity(new BeamTower());
        //this.gameField.addEntity(new NormalEnemy());
        this.gameField.addEntity(new RoketTower());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new Plane());
        this.spawner.addEnemy(new Plane());
        this.spawner.addEnemy(new Plane());
        this.spawner.addEnemy(new TankEnemy());
        this.spawner.addEnemy(new TankEnemy());
        this.spawner.addEnemy(new BossEnemy());



    }

    @Override
    public void handle(long now) {

        final double elapsed = now - lastTime;
        lastTime = now;
        lag += elapsed;
        //Todo Get input

        while (lag >= GameConfig.NPF) {
            this.gameField.updateEnemy(GameConfig.NPF);
            lag -= GameConfig.NPF;
        }

        this.render();

        this.graphicsContext.setFill(Color.GOLD);
        this.graphicsContext.fillText(String.format("%f", (double) GameConfig.NPS / elapsed), 10, 20);

        this.lastTime = now;
    }

    @Override
    public void start() {
        super.start();
        lag = 0;
        this.lastTime = System.nanoTime();
    }

    public void render() {
        for(Entity entity : gameField.getListEntries()) {
            entity.render(this.graphicsContext);
        }
    }
}
