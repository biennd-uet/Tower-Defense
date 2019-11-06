package townerdefense;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import townerdefense.entity.Entity;
import townerdefense.entity.tile.Map;

public class GameController extends AnimationTimer {

    private final GraphicsContext graphicsContext;
    private final GameField gameField;
    private long lastTime;


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
        int deltaTime = (int) (System.nanoTime() - lastTime);

        if (deltaTime < GameConfig.NPF) {
            return ;
        }

        System.out.printf("FPS : %3.2f\n", (double) GameConfig.NPS / deltaTime);

        this.gameField.updateEnemy(deltaTime);
        this.draw();

        this.lastTime = System.nanoTime();
    }

    @Override
    public void start() {
        super.start();
        this.lastTime = System.nanoTime();
    }

    public void draw() {
        for(Entity entity: gameField.getListEntries()) {
            entity.draw(graphicsContext);
        }
    }
}
