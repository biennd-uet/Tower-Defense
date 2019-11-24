package townerdefense.engine;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class GameStage implements Initializable {

    private GameField gameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public GameField getGameField() {
        return gameField;
    }

    enum Stage {
        STAGE1("Stage1");
        private String name;

        Stage(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
