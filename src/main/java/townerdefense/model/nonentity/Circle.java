package townerdefense.model.nonentity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.engine.GameConfig;

public class Circle extends NonEntity {
    private final double radius;

    public Circle(double posX, double posY, double radius) {
        super(posX, posY, radius, radius);
        this.radius = radius;
    }

    @Override
    public void setPosX(double posX) {
        this.posX = posX + (GameConfig.SIZE_TILE_WIDTH - this.radius) / 2;
    }

    @Override
    public void setPosY(double posY) {
        this.posY = posY + (GameConfig.SIZE_TILE_HEIGHT - this.radius) / 2;
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.AQUA);
        graphicsContext.strokeOval(posX, posY, radius, radius);
    }
}
