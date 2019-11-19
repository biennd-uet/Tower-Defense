package townerdefense.engine;

public class GameStage {
    private GameField gameField;

    public GameStage() {
        this.gameField = new GameField();
        initStage();
    }

    //Todo: Load information of game
    public void initStage() {

    }

    public GameField getGameField() {
        return gameField;
    }
}
