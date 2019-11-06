package townerdefense.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.entity.UpdatableEntity;

public class Target extends Tile implements UpdatableEntity {
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
