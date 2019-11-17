package townerdefense.control;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import townerdefense.engine.GameConfig;
import townerdefense.engine.GameField;
import townerdefense.engine.entity.enemy.NormalEnemy;
import townerdefense.engine.entity.other.Point;
import townerdefense.engine.entity.tile.Spawner;
import townerdefense.engine.entity.tile.Target;
import townerdefense.engine.entity.tile.map.Map;
import townerdefense.engine.entity.tile.map.WayPoint;
import townerdefense.engine.entity.tile.tower.NormalTower;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GameController extends AnimationTimer implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private Canvas canvas;
    @FXML
    private StackPane infomation;
    @FXML
    private HBox listTower;
    @FXML
    private Group gameArea;


    public static List<Point> points;
    private GraphicsContext graphicsContext;
    private GameField gameField;
    private Map map;
    private WayPoint wayPoint;
    private Spawner spawner;
    private long lastTime;
    private long lag;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("Init game...");

        canvas.setWidth(GameConfig.STAGE_WIDTH);
        canvas.setHeight(GameConfig.STAGE_HEIGHT);

        this.graphicsContext = canvas.getGraphicsContext2D();

        System.out.println("Setting game...");


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
        this.gameField.addEntity(this.spawner);
        this.gameField.addEntity(new Target());
        this.gameField.addEntity(new NormalTower());
        this.gameField.addEntity(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());
        this.spawner.addEnemy(new NormalEnemy());

        System.out.println("Start game ");

        this.start();
    }

    @Override
    public void handle(long now) {
        final double elapsed = now - lastTime;
        lastTime = now;
        lag += elapsed;

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
        GameField.entities
                .parallelStream()
                .collect(Collectors.toList())
                .forEach(entity -> entity.render(this.graphicsContext));
    }
}
