package townerdefense;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import townerdefense.entity.Entity;

public class GameController extends AnimationTimer {

    private final GraphicsContext graphicsContext;
    private final GameField gameField;
    private long startTime;


    public GameController(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        this.gameField = new GameField();
    }

    @Override
    public void handle(long now) {
        int time = (int) (System.nanoTime() - now);
        gameField.updateEnemy(time);
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
