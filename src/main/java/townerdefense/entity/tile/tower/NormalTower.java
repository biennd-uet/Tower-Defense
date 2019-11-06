package townerdefense.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;

public class NormalTower extends Tower {

    public NormalTower(double posX, double posY, double with, double height, double speed, double range, double damage) {
        super(posX, posY, with, height, speed, range, damage);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {

    }
}
