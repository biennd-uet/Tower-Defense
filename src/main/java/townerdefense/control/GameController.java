package townerdefense.control;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import townerdefense.engine.GameConfig;
import townerdefense.engine.GameField;
import townerdefense.engine.entity.TypeOfEntity;
import townerdefense.engine.entity.enemy.NormalEnemy;
import townerdefense.engine.entity.other.Point;
import townerdefense.engine.entity.tile.Spawner;
import townerdefense.engine.entity.tile.Target;
import townerdefense.engine.entity.tile.map.Map;
import townerdefense.engine.entity.tile.map.WayPoint;
import townerdefense.engine.entity.tile.tower.NormalTower;
import townerdefense.engine.entity.tile.tower.TypeOfTower;
import townerdefense.model.nonentity.NonEntity;
import townerdefense.model.nonentity.Rect;
import townerdefense.model.nonentity.Circle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GameController extends AnimationTimer implements Initializable {
    public static List<Point> points;
    @FXML
    private BorderPane root;
    @FXML
    private Group gameArea;
    @FXML
    private Canvas canvas;
    @FXML
    private StackPane infomation;
    @FXML
    private HBox listTower;
    @FXML
    private ImageView tower1;
    @FXML
    private ImageView tower2;
    @FXML
    private ImageView tower3;
    @FXML
    private ImageView tower4;


    private GraphicsContext graphicsContext;
    private GameField gameField;
    private Map map;
    private WayPoint wayPoint;
    private Spawner spawner;
    private long lastTime;
    private long lag;

    private boolean isPickedTower;
    private TypeOfTower typeOfTowerPicked;
    private boolean inPickPosition;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("Init game...");

        canvas.setWidth(GameConfig.STAGE_WIDTH);
        canvas.setHeight(GameConfig.STAGE_HEIGHT);

        this.graphicsContext = canvas.getGraphicsContext2D();
        this.isPickedTower = false;

        System.out.println("Setting game...");


        //Todo: init Game field with GameStage and get return this
        //GameStage gameStage = new GameStage();
        //this.gameField = gameStage.getGameField()
        //Todo: comment this code after finish before code

        tower1.setImage(TypeOfTower.NORMAL_TOWER.getImage());

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

        initGetEvent();

        System.out.println("Start game ");

        this.start();
    }

    @Override
    public void handle(long now) {
        final double elapsed = now - lastTime;
        lastTime = now;
        lag += elapsed;

        //Todo Handle event

        isPickedTower = false;

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
        //Todo render event

        NonEntity.nonEntities.forEach(nonEntity -> nonEntity.render(graphicsContext));
    }


    private TypeOfTower getTypeOfTower(double posX, double posY) {
        int serialOfSquare = (int) posX / GameConfig.SIZE_SQUARE_IN_BAR;

        switch (serialOfSquare) {
            case 0:
                return TypeOfTower.NORMAL_TOWER;
            default:
                break;
        }

        return null;
    }

    private TypeOfEntity getTypeOfTile(double posX, double posY) {
        int positionTileInX = (int) Math.round(posX / GameConfig.SIZE_TILE_WIDTH);
        int positionTileInY = (int) Math.round(posY / GameConfig.SIZE_TILE_HEIGHT);

        return TypeOfEntity.getTypeOfEntityByType(Map.map[positionTileInY][positionTileInX]);
    }

    private void initGetEvent() {
        listTower.setOnDragDetected(event -> {
            System.out.println("Drag detected");
        });

        tower1.setOnDragDetected(event -> {
            final double posX = GameConfig.SIZE_TILE_WIDTH * Math.round(event.getSceneX() / GameConfig.SIZE_TILE_WIDTH);
            final double posY = GameConfig.SIZE_TILE_HEIGHT * Math.round(event.getSceneY() / GameConfig.SIZE_TILE_HEIGHT);
            NonEntity.nonEntities.add(new Rect(posX, posY, GameConfig.SIZE_TILE_WIDTH, GameConfig.SIZE_TILE_HEIGHT));
            NonEntity.nonEntities.add(new Circle(posX, posY, GameConfig.TOWER_RANGE));
            Dragboard dragboard = tower1.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(tower1.getImage());

            dragboard.setContent(content);

            event.consume();

            isPickedTower = true;

            System.out.println("Pick Tower 1");
        });

        gameArea.setOnDragOver(dragEvent -> {
            //System.out.println(isPickedTower);
            dragEvent.acceptTransferModes(TransferMode.MOVE);
            final double posX = GameConfig.SIZE_TILE_WIDTH * Math.round(dragEvent.getSceneX() / GameConfig.SIZE_TILE_WIDTH);
            final double posY = GameConfig.SIZE_TILE_HEIGHT * Math.round(dragEvent.getSceneY() / GameConfig.SIZE_TILE_HEIGHT);
            //graphicsContext.strokeRect(posX, posY, GameConfig.SIZE_TILE_WIDTH, GameConfig.SIZE_TILE_HEIGHT);
            //System.out.println(dragEvent.getX() + " " + dragEvent.getY());
            NonEntity.nonEntities.forEach(nonEntity -> {
                nonEntity.setPosX(posX);
                nonEntity.setPosY(posY);
            });
        });

        gameArea.setOnDragDropped(dragEvent -> {

            dragEvent.acceptTransferModes(TransferMode.MOVE);
            Dragboard dragboard = dragEvent.getDragboard();
            if (dragboard.hasImage()) {
                if (getTypeOfTile(dragEvent.getX(), dragEvent.getY()) == TypeOfEntity.ROAD6) {
                    final double posX = GameConfig.SIZE_TILE_WIDTH * Math.round(dragEvent.getSceneX() / GameConfig.SIZE_TILE_WIDTH);
                    final double posY = GameConfig.SIZE_TILE_HEIGHT * Math.round(dragEvent.getSceneY() / GameConfig.SIZE_TILE_HEIGHT);
                    System.out.println( getTypeOfTile(dragEvent.getX(), dragEvent.getY()) + "" + posX + " " + posY);
                    gameField.addEntity(new NormalTower(posX, posY));
                    isPickedTower = false;
                    System.out.println("Add new tower...");
                }
                NonEntity.nonEntities.clear();
            } else {
                dragEvent.setDropCompleted(false);
            }
            System.out.println("Dropped on " + dragEvent.getX() + " " + dragEvent.getY());
            dragEvent.consume();
        });
    }
}
