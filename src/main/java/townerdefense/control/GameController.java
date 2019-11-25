package townerdefense.control;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Pair;
import townerdefense.engine.GameConfig;
import townerdefense.engine.GameField;
import townerdefense.engine.entity.TypeOfEntity;
import townerdefense.engine.entity.other.Point;
import townerdefense.engine.entity.tile.Spawner;
import townerdefense.engine.entity.tile.map.Map;
import townerdefense.engine.entity.tile.map.WayPoint;
import townerdefense.engine.entity.tile.tower.*;
import townerdefense.model.GameManager;
import townerdefense.model.UserManager;
import townerdefense.model.nonentity.Circle;
import townerdefense.model.nonentity.NonEntity;
import townerdefense.model.nonentity.Rect;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GameController extends AnimationTimer implements Initializable {
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
    private ImageView sellImage;
    @FXML
    private Text towerName;
    @FXML
    private Text towerPrice;
    @FXML
    private Text towerDamage;
    @FXML
    private Text towerRange;
    @FXML
    private TextFlow towerDiscription;
    @FXML
    private Button pauseButton;
    @FXML
    private Button backToMenuButton;

    private GraphicsContext graphicsContext;
    private long lastTime;
    private long lag;
    private TypeOfTower typeOfTowerPicked;
    private GameManager gameManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("Init game...");

        this.graphicsContext = canvas.getGraphicsContext2D();
        canvas.setWidth(GameConfig.STAGE_WIDTH);
        canvas.setHeight(GameConfig.STAGE_HEIGHT);

        gameManager = GameManager.getInstance();

        initUserInterface();

        initSetOnEvent();

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

        health.setText(String.valueOf(0));
        gold.setText(String.valueOf(0));
        stage.setText(String.valueOf(0));

    }

    private void initSetOnEvent() {

        System.out.println("Init event..");
        listTower.setOnDragDetected(event -> {
            System.out.println("Drag detected");
        });

        normalTower.setOnDragDetected(event -> {
            typeOfTowerPicked = TypeOfTower.NormalTower;
            pickTower(event, normalTower, typeOfTowerPicked);
            System.out.println("Pick Tower 1");
        });

        normalTower.setOnMousePressed(event -> {
            updateDescription(TypeOfTower.NormalTower);
        });

        rocketTower.setOnDragDetected(event -> {
            typeOfTowerPicked = TypeOfTower.RocketTower;
            pickTower(event, rocketTower, typeOfTowerPicked);
            System.out.println("Pick Tower 2");
        });

        rocketTower.setOnMousePressed(event -> {
            updateDescription(TypeOfTower.RocketTower);
        });

        beamTower.setOnDragDetected(event -> {
            typeOfTowerPicked = TypeOfTower.BeamTower;
            pickTower(event, beamTower, typeOfTowerPicked);
            System.out.println("Pick Tower 3");
        });

        beamTower.setOnMousePressed(event -> {
            updateDescription(TypeOfTower.BeamTower);
        });

        machinegunTower.setOnDragDetected(event -> {
            typeOfTowerPicked = TypeOfTower.MachineGunTower;
            pickTower(event, machinegunTower, typeOfTowerPicked);
            System.out.println("Pick Tower 4");
        });

        machinegunTower.setOnMousePressed(event -> {
            updateDescription(TypeOfTower.MachineGunTower);
        });

        sellImage.setOnDragDetected(event -> {
            final double posX = GameConfig.SIZE_TILE_WIDTH * Math.round(event.getSceneX() / GameConfig.SIZE_TILE_WIDTH);
            final double posY = GameConfig.SIZE_TILE_HEIGHT * Math.round(event.getSceneY() / GameConfig.SIZE_TILE_HEIGHT);
            NonEntity.nonEntities.add(new Rect(posX, posY, GameConfig.SIZE_TILE_WIDTH, GameConfig.SIZE_TILE_HEIGHT));
            Dragboard dragboard = sellImage.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(sellImage.getImage());

            dragboard.setContent(content);

            event.consume();
        });

        gameArea.setOnDragOver(dragEvent -> {
            final double posX = GameConfig.SIZE_TILE_WIDTH * Math.round(dragEvent.getSceneX() / GameConfig.SIZE_TILE_WIDTH);
            final double posY = GameConfig.SIZE_TILE_HEIGHT * Math.round(dragEvent.getSceneY() / GameConfig.SIZE_TILE_HEIGHT);
            NonEntity.nonEntities.forEach(nonEntity -> {
                nonEntity.setPosX(posX);
                nonEntity.setPosY(posY);
            });
            if (gameManager.isSelling()) {
                pickTowerToSell(dragEvent, posX, posY);
            } else {
                selectTilePutTower(dragEvent, posX, posY);
            }
        });

        gameArea.setOnDragDropped(dragEvent -> {
            if (dragEvent.getDragboard().hasImage()) {
                if (gameManager.isSelling()) {
                    sellTower(dragEvent);
                } else {
                    putTower(dragEvent);
                }
            }

            System.out.println("Dropped on " + dragEvent.getX() + " " + dragEvent.getY());
        });

        gameArea.setOnDragExited(event -> {
            gameManager.setSelling(false);
            NonEntity.nonEntities.clear();
        });
    }

    @Override
    public void handle(long now) {
        final double elapsed = now - lastTime;
        this.lastTime = now;
        lag += elapsed;

        while (lag >= GameConfig.NPF) {
            this.gameManager.getGameField().updateEnemy(GameConfig.NPF);
            lag -= GameConfig.NPF;
        }

        this.render();

        this.graphicsContext.setFill(Color.GOLD);
        this.graphicsContext.fillText(String.format("%f", (double) GameConfig.NPS / elapsed), 10, 20);
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
        gold.setText(String.valueOf(UserManager.getInstance().getGold()));
        UserManager.getInstance().nextTurn(gameManager.getSpawner().getNStage());
        stage.setText(String.valueOf(UserManager.getInstance().getStage()));
    }

    private TypeOfEntity getTypeOfTile(double posX, double posY) {
        int positionTileInX = (int) Math.round(posX / GameConfig.SIZE_TILE_WIDTH);
        int positionTileInY = (int) Math.round(posY / GameConfig.SIZE_TILE_HEIGHT);

        return TypeOfEntity.getTypeOfEntityByType(Map.map[positionTileInY][positionTileInX]);
    }

    private void putTowerToTile(double posX, double posY, TypeOfTower typeOfTower) {
        Tower tower = null;
        switch (typeOfTowerPicked) {
            case NormalTower:
                tower = new NormalTower(posX, posY);
                break;
            case RocketTower:
                tower = new RocketTower(posX, posY);
                break;
            case BeamTower:
                tower = new BeamTower(posX, posY);
                break;
            case MachineGunTower:
                tower = new MachineGunTower(posX, posY);
                break;
            default:
                break;
        }
        gameManager.putTower(posX, posY, tower);
    }

    private void pickTower(MouseEvent event, ImageView tower1, TypeOfTower typeOfTowerPicked) {
        if (!UserManager.getInstance().canBuyTower(typeOfTowerPicked.getPrice())) {
            event.consume();
            return;
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

    private void putTower(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasImage()) {
            if (getTypeOfTile(dragEvent.getX(), dragEvent.getY()) == TypeOfEntity.ROAD6) {
                final double posX = GameConfig.SIZE_TILE_WIDTH * Math.round(dragEvent.getSceneX() / GameConfig.SIZE_TILE_WIDTH);
                final double posY = GameConfig.SIZE_TILE_HEIGHT * Math.round(dragEvent.getSceneY() / GameConfig.SIZE_TILE_HEIGHT);
                if (gameManager.hasTowerInTile(posX, posY)) {
                    dragEvent.acceptTransferModes(TransferMode.NONE);
                } else {
                    dragEvent.acceptTransferModes(TransferMode.MOVE);
                    UserManager.getInstance().buyTower(typeOfTowerPicked.getPrice());
                    putTowerToTile(posX, posY, typeOfTowerPicked);
                    gold.setText(String.valueOf(UserManager.getInstance().getGold()));
                }
            }
            NonEntity.nonEntities.clear();
        }
        dragEvent.consume();
    }

    private void selectTilePutTower(DragEvent dragEvent, double posX, double posY) {
        if (gameManager.hasTowerInTile(posX, posY)) {
            dragEvent.acceptTransferModes(TransferMode.NONE);
            this.graphicsContext.setStroke(Color.RED);
        } else {
            dragEvent.acceptTransferModes(TransferMode.MOVE);
            this.graphicsContext.setStroke(Color.AQUA);
        }
    }

    private void pickTowerToSell(DragEvent dragEvent, double posX, double posY) {
        if (!gameManager.hasTowerInTile(posX, posY)) {
            dragEvent.acceptTransferModes(TransferMode.NONE);
            this.graphicsContext.setStroke(Color.RED);
        } else {
            dragEvent.acceptTransferModes(TransferMode.MOVE);
            this.graphicsContext.setStroke(Color.YELLOW);
        }
    }

    private void sellTower(DragEvent dragEvent) {
        final double posX = GameConfig.SIZE_TILE_WIDTH * Math.round(dragEvent.getSceneX() / GameConfig.SIZE_TILE_WIDTH);
        final double posY = GameConfig.SIZE_TILE_HEIGHT * Math.round(dragEvent.getSceneY() / GameConfig.SIZE_TILE_HEIGHT);
        if (!gameManager.hasTowerInTile(posX, posY)) {
            dragEvent.acceptTransferModes(TransferMode.MOVE);
            UserManager.getInstance().getReward(TypeOfTower.getTypeOfTowerByClass(gameManager.getTowerFromTile(posX, posY)).getPrice() / 2);
            gameManager.removeTowerFromTile(posX, posY);
            gameManager.setSelling(false);
        } else {
            dragEvent.acceptTransferModes(TransferMode.NONE);
            dragEvent.setDropCompleted(true);
            System.out.println("Cancel Selling");
        }
        dragEvent.consume();
    }

    private void updateDescription(TypeOfTower typeOfTower) {
        towerName.setText(typeOfTower.name());
        towerDamage.setText(String.format("%d dame / per attack.", typeOfTower.getDamage()));
        towerRange.setText(String.format("%d unit.", (int) typeOfTower.getRange()));
        towerPrice.setText(String.format("$ %d", typeOfTower.getPrice()));
        towerDiscription.getChildren().clear();
        towerDiscription.getChildren().add(new Text("Some thing about tower"));
    }

    private void doPauseGame() {
        this.stop();
        pauseButton.setText("Play");
    }

    private void doPlayGame() {
        this.start();
        pauseButton.setText("Pause");
    }

    @FXML
    public void setOnHandlePause(ActionEvent event) {
        if (gameManager.isPlaying()) {
            doPauseGame();
        } else {
            doPlayGame();
        }
        gameManager.setPlaying(!gameManager.isPlaying());
    }

    @FXML
    public void setOnHandleBackToMenu(ActionEvent event) throws IOException {

        doPauseGame();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MenuView.fxml")));

        Scene scene = new Scene(root, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        stage.setScene(scene);

        stage.show();
    }

}
