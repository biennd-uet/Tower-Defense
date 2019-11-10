package townerdefense;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.entity.Entity;
import townerdefense.entity.enemy.NormalEnemy;
import townerdefense.entity.other.Point;
import townerdefense.entity.tile.Map;
import townerdefense.entity.tile.Spawner;
import townerdefense.entity.tile.Target;
import townerdefense.entity.tile.map.WayPoint;

import java.util.List;

public class GameController extends AnimationTimer {

    public static List<Point> points;
    private final GraphicsContext graphicsContext;
    private final GameField gameField;
    private final Map map;
    private final WayPoint wayPoint;
    private long lastTime;


    public GameController(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        //Todo: init Game field with GameStage and get return this
        //GameStage gameStage = new GameStage();
        //this.gameField = gameStage.getGameField()
        //Todo: comment this code after finish before code
        map = new Map();
        wayPoint = new WayPoint();
        points = wayPoint.getPoints();

        this.gameField = new GameField();

        this.gameField.addAllEntity(map.getListTile());
        this.gameField.addEntity(new WayPoint());
        this.gameField.addEntity(new Spawner());
        this.gameField.addEntity(new Target());
        this.gameField.addEntity(new NormalEnemy());
    }

    @Override
    public void handle(long now) {
        int deltaTime = (int) (System.nanoTime() - lastTime);

//        if (deltaTime < GameConfig.NPF) {
//            return ;
//        }

        //System.out.printf("FPS : %3.2f\n", (double) GameConfig.NPS / deltaTime);

        this.gameField.updateEnemy(deltaTime);
        this.render();
        this.graphicsContext.setFill(Color.GOLD);
        this.graphicsContext.fillText(String.format("%f", (double) GameConfig.NPS / deltaTime), 10, 20);

        this.lastTime = System.nanoTime();
    }

    @Override
    public void start() {
        super.start();
        this.lastTime = System.nanoTime();
    }

    public void render() {
        for(Entity entity : gameField.getListEntries()) {
            entity.render(this.graphicsContext);
        }
    }
}
