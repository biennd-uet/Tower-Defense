package townerdefense.entity;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.entity.GameEntity;

public interface DrawableEntity {
    public void render(GraphicsContext graphicsContext);
    public double getRealPosX();
    public double getRealPosY();
}
