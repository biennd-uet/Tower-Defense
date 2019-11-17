package townerdefense.engine.entity.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import townerdefense.engine.GameConfig;


public class NormalEnemy extends Enemy {

    private NormalEnemy(Image image, double r, double posX, double posY, double with, double height, double health, double speed, double armor, double reward) {
        super(image, r, posX, posY, with, height, health, speed, armor, reward);
    }

    public NormalEnemy() {
        this(GameConfig.IMEnemy, 0, GameConfig.SPAWNER_DEFAULT_POSX, GameConfig.SPAWNER_DEFAULT_POSY,
                GameConfig.NORMAL_ENEMY_WIDTH, GameConfig.NORMAL_ENEMY_HEIGHT,
                GameConfig.NORMAL_ENEMY_HEALTH_MAX, GameConfig.NORMAL_ENEMY_SPEED,
                GameConfig.NORMAL_ENEMY_ARMOR, GameConfig.NORMAL_ENEMY_REWARD);
    }

    public NormalEnemy(double posX, double posY) {
        this(GameConfig.IMEnemy, 0, posX, posY,
                GameConfig.NORMAL_ENEMY_WIDTH, GameConfig.NORMAL_ENEMY_HEIGHT,
                GameConfig.NORMAL_ENEMY_HEALTH_MAX, GameConfig.NORMAL_ENEMY_SPEED,
                GameConfig.NORMAL_ENEMY_ARMOR, GameConfig.NORMAL_ENEMY_REWARD);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);

        final double percentHealth = health / GameConfig.NORMAL_ENEMY_HEALTH_MAX;
        if(percentHealth <= 0.25) {
            graphicsContext.setFill(Color.RED);
        } else if(percentHealth <= 0.5) {
            graphicsContext.setFill(Color.ORANGE);
        } else {
            graphicsContext.setFill(Color.LIGHTGREEN);
        }

        graphicsContext.fillRect(this.getPosX(), this.getPosY() + 2,
                width * percentHealth, 2);
    }
}
