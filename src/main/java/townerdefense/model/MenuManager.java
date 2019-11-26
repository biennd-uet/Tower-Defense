package townerdefense.model;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.net.URL;

public class MenuManager {
    public static final URL mainMenuUrl;
    public static final URL settingUrl;
    public static final URL aboutUrl;
    public static final URL gameUrl;

    static {
        mainMenuUrl = MenuManager.class.getResource("/fxml/MenuView.fxml");
        settingUrl = MenuManager.class.getResource("/fxml/SettingView.fxml");
        aboutUrl = MenuManager.class.getResource("/fxml/AboutView.fxml");
        gameUrl = MenuManager.class.getResource("/fxml/GameView.fxml");
    }

    private MenuManager() {

    }
}
