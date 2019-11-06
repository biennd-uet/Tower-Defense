package townerdefense.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Mountain extends Tile {
    public Mountain(double posX, double posY, double with, double height) {
        super(posX, posY, with, height);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.BROWN);
        graphicsContext.fillRect(posX, posY, posX + with, posY + height);
    }
}
