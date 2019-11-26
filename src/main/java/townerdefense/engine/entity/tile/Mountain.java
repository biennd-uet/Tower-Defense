package townerdefense.engine.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.engine.GameConfig;

public class Mountain extends Tile {

    public Mountain(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(GameConfig.IMMountain, posX, posY, width, height);
    }
}
