package townerdefense.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Road extends Tile {
    public Road(double posX, double posY, double with, double height) {
        super(posX, posY, with, height);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.LIGHTGREEN);
        graphicsContext.fillRect(posX, posY, posX + with, posY + height);
    }
}
