package townerdefense.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.entity.UpdateableEntity;

public class Target extends Tile implements UpdateableEntity {
    public Target(double posX, double posY, double with, double height) {
        super(posX, posY, with, height);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {

    }

    @Override
    public void update(int deltaTime) {

    }
}
