package townerdefense.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import townerdefense.engine.GameConfig;
import townerdefense.model.SettingManager;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private CheckBox soundCheckbox;
    @FXML
    private CheckBox musicCheckBox;
    private SettingManager settingManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Init SettingManager");
        this.settingManager = SettingManager.getInstance();
        soundCheckbox.setSelected(settingManager.hasSound());
        musicCheckBox.setSelected(settingManager.hasMusic());
    }

    @FXML
    public void handleCancelButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MenuView.fxml")));

        Scene scene = new Scene(root, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

        stage.setScene(scene);

        stage.show();
    }

    @FXML
    public void switchStateSound(ActionEvent event) {
        if (soundCheckbox.isSelected()) {
            settingManager.turnOnSound();
        } else {
            settingManager.turnOffSound();
        }
    }

    @FXML
    public void switchSateMusic(ActionEvent event) {
        if (musicCheckBox.isSelected()) {
            settingManager.turnOnMusic();
        } else {
            settingManager.turnOffMusic();
        }
    }
}
