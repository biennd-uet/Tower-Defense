package townerdefense.drawer;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.entity.GameEntity;

public interface Drawable {
    public void draw(GraphicsContext graphicsContext, GameEntity gameEntity, double posX, double posY, double ration);
}
