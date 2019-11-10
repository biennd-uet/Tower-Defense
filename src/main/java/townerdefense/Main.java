package townerdefense;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public final class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Group root = new Group();
        final Canvas canvas = new Canvas(GameConfig.STAGE_WIDTH, GameConfig.STAGE_HEIGHT);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        final GameController gameController = new GameController(graphicsContext);

        root.getChildren().add(canvas);

        Scene scene = new Scene(root, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.setTitle(GameConfig.GAME_TITLE);
        primaryStage.setResizable(false);

        primaryStage.show();

        gameController.start();
    }
}
