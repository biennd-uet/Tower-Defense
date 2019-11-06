package townerdefense;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import townerdefense.entity.Entity;
import townerdefense.entity.tile.Map;

import java.util.Collection;
import java.util.List;

public class GameController extends AnimationTimer {

    private final GraphicsContext graphicsContext;
    private final GameField gameField;
    private long startTime;


    public GameController(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        //Todo init Game field with GameStage and get return this
        //GameStage gameStage = new GameStage();
        //this.gameField = gameStage.getGameField()
        //Todo comment this code after finish before code
        this.gameField = new GameField();
        Map map = new Map();
        this.gameField.addAllEntity(map.getListTile());
    }

    @Override
    public void handle(long now) {
        int time = (int) (System.nanoTime() - now);
        gameField.updateEnemy(time);
        this.draw();
    }

    @Override
    public void start() {
        super.start();
        this.startTime = System.nanoTime();
    }

    public void draw() {
        for(Entity entity: gameField.getListEntries()) {
            entity.draw(graphicsContext);
        }
    }
}
