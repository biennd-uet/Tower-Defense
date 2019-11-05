package townerdefense;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import townerdefense.drawer.Drawer;

public class GameController extends AnimationTimer {

    private final GraphicsContext graphicsContext;
    private Drawer drawer;
    private long startTime;

    public GameController(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }



    @Override
    public void handle(long now) {
        int time = (int) (System.nanoTime() - now);
    }

    @Override
    public void start() {
        super.start();
        this.startTime = System.nanoTime();
    }
}
