package townerdefense.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Road extends Tile {
    public Road(double posX, double posY, double with, double height) {
        super(posX, posY, with, height);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillRect(posX, posY, posX + with, posY + height);
        //System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return String.format("Road %f %f %f %f\n", this.posX, this.posY, this.with, this.height);
    }
}
