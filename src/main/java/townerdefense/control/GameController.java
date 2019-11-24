package townerdefense.control;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import townerdefense.engine.GameConfig;
import townerdefense.engine.GameField;
import townerdefense.engine.entity.TypeOfEntity;
import townerdefense.engine.entity.other.Point;
import townerdefense.engine.entity.tile.Spawner;
import townerdefense.engine.entity.tile.map.Map;
import townerdefense.engine.entity.tile.map.WayPoint;
import townerdefense.engine.entity.tile.tower.*;
import townerdefense.model.UserManager;
import townerdefense.model.nonentity.Circle;
import townerdefense.model.nonentity.NonEntity;
import townerdefense.model.nonentity.Rect;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GameController extends AnimationTimer implements Initializable {
    public static List<Point> points;
    private boolean isPlaying;
    @FXML
    private BorderPane root;
    @FXML
    private Group gameArea;
    @FXML
    private Canvas canvas;
    @FXML
    private HBox information;
    @FXML
    private ImageView imageHealth;
    @FXML
    private Text health;
    @FXML
    private ImageView imageGold;
    @FXML
    private Text gold;
    @FXML
    private ImageView imageTurn;
    @FXML
    private Text turn;
    @FXML
    private ImageView imageStage;
    @FXML
    private Text stage;
    @FXML
    private HBox listTower;
    @FXML
    private ImageView normalTower;
    @FXML
    private Text normalTowerPrice;
    @FXML
    private ImageView machinegunTower;
    @FXML
    private Text machinegunTowerPrice;
    @FXML
    private ImageView rocketTower;
    @FXML
    private Text rocketTowerPrice;
    @FXML
    private ImageView beamTower;
    @FXML
    private Text beamTowerPrice;
    @FXML
    private Text towerName;
    @FXML
    private Text towerPrice;
    @FXML
    private Text towerDamage;
    @FXML
    private Text towerRange;
    @FXML
    private Text towerDiscription;
    @FXML
    private Button pauseButton;


    private GraphicsContext graphicsContext;
    private GameField gameField;
    private Map map;
    private WayPoint wayPoint;
    private Spawner spawner;
    private long lastTime;
    private long lag;
    public static UserManager user;

    private TypeOfTower typeOfTowerPicked;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("Init game...");

        initSetting();

        initUser();

        initUserInterface();

        initSetOnEvent();

        initGameField();

        System.out.println("Start game ");

        this.start();
    }

    private void initUserInterface() {
        System.out.println("Init User Interface");
        normalTower.setImage(TypeOfTower.NormalTower.getImage());
        rocketTower.setImage(TypeOfTower.RocketTower.getImage());
        beamTower.setImage(TypeOfTower.BeamTower.getImage());
        machinegunTower.setImage(TypeOfTower.MachineGunTower.getImage());

        normalTowerPrice.setText(String.format("$ %d", TypeOfTower.NormalTower.getPrice()));
        rocketTowerPrice.setText(String.format("$ %d", TypeOfTower.RocketTower.getPrice()));
        beamTowerPrice.setText(String.format("$ %d", TypeOfTower.BeamTower.getPrice()));
        machinegunTowerPrice.setText(String.format("$ %d", TypeOfTower.MachineGunTower.getPrice()));

        health.setText(String.valueOf(user.getHealth()));
        gold.setText(String.valueOf(user.getGold()));
        stage.setText(String.valueOf(user.getStage()));

        if (!isPlaying) {
            pauseButton.setText("Play");
        }
    }

    private void initSetting() {
        isPlaying = true;
        canvas.setWidth(GameConfig.STAGE_WIDTH);
        canvas.setHeight(GameConfig.STAGE_HEIGHT);

        this.graphicsContext = canvas.getGraphicsContext2D();

        System.out.println("Setting game...");
    }

    private void initGameField() {
        //Todo: init Game field with GameStage and get return this
        //GameStage gameStage = new GameStage();
        //this.gameField = gameStage.getGameField()
        //Todo: comment this code after finish before code

        try {
            this.map = new Map();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        this.wayPoint = new WayPoint();

        points = wayPoint.getPoints();
        this.gameField = new GameField();

        this.gameField.addAllEntity(map.getListTile());
        this.spawner = new Spawner();
        this.gameField.addEntity(this.spawner);

    }

    private void initUser() {
        user = new UserManager(100, 100, 0, 1);
    }

    @Override
    public void handle(long now) {
        final double elapsed = now - lastTime;
        //System.out.println(now);
        lastTime = now;
        lag += elapsed;

        //Todo Handle event

        while (lag >= GameConfig.NPF) {
            this.gameField.updateEnemy(GameConfig.NPF);
            lag -= GameConfig.NPF;
        }
        user.nextTurn(this.spawner.getNStage());
        stage.setText(String.valueOf(user.getStage()));
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

        NonEntity.nonEntities.forEach(nonEntity -> nonEntity.render(graphicsContext));
        gold.setText(String.valueOf(user.getGold()));
    }

    private TypeOfEntity getTypeOfTile(double posX, double posY) {
        int positionTileInX = (int) Math.round(posX / GameConfig.SIZE_TILE_WIDTH);
        int positionTileInY = (int) Math.round(posY / GameConfig.SIZE_TILE_HEIGHT);

        return TypeOfEntity.getTypeOfEntityByType(Map.map[positionTileInY][positionTileInX]);
    }

    private void setOnDragDetectedPickerBar(MouseEvent event, ImageView tower1, TypeOfTower typeOfTowerPicked) {
        if (!user.canBuyTower(typeOfTowerPicked.getPrice())) {
            event.consume();
            return ;
        }
        final double posX = GameConfig.SIZE_TILE_WIDTH * Math.round(event.getSceneX() / GameConfig.SIZE_TILE_WIDTH);
        final double posY = GameConfig.SIZE_TILE_HEIGHT * Math.round(event.getSceneY() / GameConfig.SIZE_TILE_HEIGHT);
        NonEntity.nonEntities.add(new Rect(posX, posY, GameConfig.SIZE_TILE_WIDTH, GameConfig.SIZE_TILE_HEIGHT));
        NonEntity.nonEntities.add(new Circle(posX, posY, typeOfTowerPicked.getRange()));
        Dragboard dragboard = tower1.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putImage(tower1.getImage());

        dragboard.setContent(content);

        event.consume();
    }

    private void setOnDragDroppedGameArea(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.MOVE);
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragboard.hasImage()) {
            if (getTypeOfTile(dragEvent.getX(), dragEvent.getY()) == TypeOfEntity.ROAD6) {
                final double posX = GameConfig.SIZE_TILE_WIDTH * Math.round(dragEvent.getSceneX() / GameConfig.SIZE_TILE_WIDTH);
                final double posY = GameConfig.SIZE_TILE_HEIGHT * Math.round(dragEvent.getSceneY() / GameConfig.SIZE_TILE_HEIGHT);
                //System.out.printf("%s - (%f %f)\n", typeOfTowerPicked, posX, posY);

                switch (typeOfTowerPicked) {
                    case NormalTower:
                        gameField.addEntity(new NormalTower(posX, posY));
                        break;
                    case RocketTower:
                        gameField.addEntity(new RocketTower(posX, posY));
                        break;
                    case BeamTower:
                        gameField.addEntity(new BeamTower(posX, posY));
                        break;
                    case MachineGunTower:
                        gameField.addEntity(new MachineGunTower(posX, posY));
                        break;
                    default:
                        break;
                }

                user.buyTower(typeOfTowerPicked.getPrice());
                gold.setText(String.valueOf(user.getGold()));

            }
            NonEntity.nonEntities.clear();
        } else {
            dragEvent.setDropCompleted(false);
        }
        dragEvent.consume();
    }

    private void setOnMousePressed(TypeOfTower typeOfTower) {
        updateDescription(typeOfTower);
    }


    private void initSetOnEvent() {

        System.out.println("Init event..");
        listTower.setOnDragDetected(event -> {
            System.out.println("Drag detected");
        });

        pauseButton.setOnMousePressed(event -> {
            if (isPlaying) {
                this.stop();
                pauseButton.setText("Play");
            } else {
                this.start();
                pauseButton.setText("Pause");
            }
            isPlaying = !isPlaying;
        });

        normalTower.setOnDragDetected(event -> {
            typeOfTowerPicked = TypeOfTower.NormalTower;
            setOnDragDetectedPickerBar(event, normalTower, typeOfTowerPicked);
            System.out.println("Pick Tower 1");
        });

        normalTower.setOnMousePressed(event -> {
            updateDescription(TypeOfTower.NormalTower);
        });

        rocketTower.setOnDragDetected(event -> {
            typeOfTowerPicked = TypeOfTower.RocketTower;
            setOnDragDetectedPickerBar(event, rocketTower, typeOfTowerPicked);
            System.out.println("Pick Tower 2");
        });

        rocketTower.setOnMousePressed(event -> {
            updateDescription(TypeOfTower.RocketTower);
        });

        beamTower.setOnDragDetected(event -> {
            typeOfTowerPicked = TypeOfTower.BeamTower;
            setOnDragDetectedPickerBar(event, beamTower, typeOfTowerPicked);
            System.out.println("Pick Tower 3");
        });

        beamTower.setOnMousePressed(event -> {
            updateDescription(TypeOfTower.BeamTower);
        });

        machinegunTower.setOnDragDetected(event -> {
            typeOfTowerPicked = TypeOfTower.MachineGunTower;
            setOnDragDetectedPickerBar(event, machinegunTower, typeOfTowerPicked);
            System.out.println("Pick Tower 4");
        });

        machinegunTower.setOnMousePressed(event -> {
            updateDescription(TypeOfTower.MachineGunTower);
        });

        gameArea.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.MOVE);
            final double posX = GameConfig.SIZE_TILE_WIDTH * Math.round(dragEvent.getSceneX() / GameConfig.SIZE_TILE_WIDTH);
            final double posY = GameConfig.SIZE_TILE_HEIGHT * Math.round(dragEvent.getSceneY() / GameConfig.SIZE_TILE_HEIGHT);
            NonEntity.nonEntities.forEach(nonEntity -> {
                nonEntity.setPosX(posX);
                nonEntity.setPosY(posY);
            });
        });

        gameArea.setOnDragDropped(dragEvent -> {
            setOnDragDroppedGameArea(dragEvent);
            System.out.println("Dropped on " + dragEvent.getX() + " " + dragEvent.getY());
        });
    }

    private void updateDescription(TypeOfTower typeOfTower) {
        towerName.setText(typeOfTower.name());
        towerDamage.setText(String.format("%d dame / per attack.", typeOfTower.getDamage()));
        towerRange.setText(String.format("%d unit.", (int) typeOfTower.getRange()));
        towerPrice.setText(String.format("$ %d", typeOfTower.getPrice()));
        towerDiscription.setText("Some thing about tower");
    }
}
