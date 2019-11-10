package townerdefense.entity.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.GameConfig;
import townerdefense.entity.other.Point;

public class NormalEnemy extends Enemy {

    public NormalEnemy(double posX, double posY, double with, double height, double health, double speed, double armor, double reward) {
        super(posX, posY, with, height, health, speed, armor, reward);
    }

    public NormalEnemy() {
        this(GameConfig.SPAWNER_DEFAULT_POSX, GameConfig.SPAWNER_DEFAULT_POSY,
                GameConfig.NORMAL_ENEMY_WIDTH, GameConfig.NORMAL_ENEMY_HEIGHT,
                GameConfig.NORMAL_ENEMY_HEALTH_MAX, GameConfig.NORMAL_ENEMY_SPEED,
                GameConfig.NORMAL_ENEMY_ARMOR, GameConfig.NORMAL_ENEMY_REWARD);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.AQUA);
        graphicsContext.fillOval(this.getCenterPosX(), this.getCenterPosY(), this.width, this.height);
    }
}
