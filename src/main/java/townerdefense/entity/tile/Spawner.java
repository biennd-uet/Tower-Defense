package townerdefense.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.entity.UpdatableEntity;

public class Spawner extends Tile implements UpdatableEntity {
    public Spawner(double posX, double posY, double with, double height) {
        super(posX, posY, with, height);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {

    }

    @Override
    public void update(int tick) {

    }
}
