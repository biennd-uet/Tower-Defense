package townerdefense.engine.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.engine.GameConfig;

public class Road extends Tile {

    public Road(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(GameConfig.IMRoad, posX, posY, width, height);
    }

    @Override
    public String toString() {
        return String.format("Road %f %f %f %f\n", this.posX, this.posY, this.width, this.height);
    }
}
