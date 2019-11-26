package townerdefense;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import townerdefense.control.MenuController;
import townerdefense.engine.GameConfig;

import java.util.Objects;

public final class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/fxml/MenuView.fxml")));

        Parent root = fxmlLoader.load();

        MenuController menuController = fxmlLoader.getController();

        reloadSetting();

        menuController.setStage(primaryStage);

        Scene scene = new Scene(root, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.setTitle(GameConfig.GAME_TITLE);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    private void reloadSetting() {
    }
}
