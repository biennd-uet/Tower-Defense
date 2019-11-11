package townerdefense.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.GameConfig;
import townerdefense.entity.UpdatableEntity;

public class Target extends Tile implements UpdatableEntity {
    public Target(double posX, double posY, double with, double height) {
        super(posX, posY, with, height);
    }

    public Target() {
        this(GameConfig.TARGET_DEFAULT_POSX, GameConfig.TARGET_DEFAULT_POSY,
                GameConfig.TARGET_WIDTH, GameConfig.TARGET_HEIGHT);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(this.posX, this.posY, this.width, this.height);
    }

    @Override
    public void update(int deltaTime) {

    }
}
