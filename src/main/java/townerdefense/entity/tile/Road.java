package townerdefense.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Road extends Tile {
    private Image image;

    public Road(Image image, double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        this.image = image;
    }

    public Road(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        //  graphicsContext.setFill(Color.GREEN);
        //   graphicsContext.fillRect(posX, posY, posX + width, posY + height);
        //System.out.println(this.toString());
        graphicsContext.drawImage(image, posX, posY, width, height);
    }

    @Override
    public String toString() {
        return String.format("Road %f %f %f %f\n", this.posX, this.posY, this.width, this.height);
    }
}
