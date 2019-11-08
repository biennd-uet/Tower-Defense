package townerdefense.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NormalTower extends Tower {

//    public NormalTower(Image image,double posX, double posY, double with, double height, double speed, double range, double damage) {
//        super(image,posX, posY, with, height, speed, range, damage);
//    }


    public NormalTower(double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(posX, posY, width, height, speed, range, damage);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {

    }

    @Override
    public void update(int deltaTime) {

    }
}
