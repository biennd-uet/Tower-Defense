package townerdefense.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import townerdefense.engine.GameConfig;
import townerdefense.model.GameManager;
import townerdefense.model.MenuManager;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private Button aboutButton;
    @FXML
    private Button continueButton;
    @FXML
    private Button newGameButton;
    @FXML
    private Button settingButton;
    @FXML
    private Button exitButton;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Font.loadFont(this.getClass().getResource("/fonts/Freedom-nZ4J.otf").toExternalForm(), 12);
    }

    @FXML
    protected void handleContinueButtonAction(ActionEvent event) throws IOException {
        loadNewScene(MenuManager.gameUrl);

        stage.show();

        System.out.println("Load Scene...");
    }

    @FXML
    public void handleNewGameButton(ActionEvent event) throws IOException {
        loadNewScene(MenuManager.gameUrl);

        stage.show();

        System.out.println("Load Scene...");
    }

    @FXML
    public void handleAboutButtonAction(ActionEvent event) throws IOException {
        loadNewScene(MenuManager.aboutUrl);

        stage.show();
    }

    @FXML
    public void handleExitButtonAction(ActionEvent event) {
        stage.close();
    }

    @FXML
    public void handleSettingButtonAction(ActionEvent event) throws IOException {

        loadNewScene(MenuManager.settingUrl);

        stage.show();

        System.out.println("Go to SettingManager!");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void loadNewScene(URL url) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(url), GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
    }
}
