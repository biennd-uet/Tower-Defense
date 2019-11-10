package townerdefense.entity.tile.tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import townerdefense.GameConfig;

public class NormalTower extends Tower {

    public NormalTower(Image image, double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(image, posX, posY, width, height, speed, range, damage);
    }

    //Default
    public NormalTower() {
        this(null, GameConfig.TOWER_DEFAULT_POSX, GameConfig.TOWER_DEFAULT_POSY,
                GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.TOWER_SPEED, GameConfig.TOWER_RANGE, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
    }

    @Override
    public void update(int deltaTime) {

    }
}
