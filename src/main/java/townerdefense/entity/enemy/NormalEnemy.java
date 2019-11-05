package townerdefense.entity.enemy;

import javafx.scene.canvas.GraphicsContext;

public class NormalEnemy extends Enemy {

    public NormalEnemy(double posX, double posY, double with, double height, double health, double speed, double armor, double reward) {
        super(posX, posY, with, height, health, speed, armor, reward);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {

    }
}
