package townerdefense.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Mountain extends Tile {
    protected Image image;
    public Mountain(Image image,double posX, double posY, double with, double height) {
        super(posX, posY, with, height);
        this.image = image;
    }

    public Mountain(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.LIGHTBLUE);
        graphicsContext.fillRect(posX, posY, posX + width, posY + height);
    }
}
