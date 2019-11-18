package townerdefense.engine.entity.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import townerdefense.engine.GameConfig;


public class TankEnemy extends Enemy {

    private TankEnemy(Image image, double r, double posX, double posY, double with, double height, double health, double speed, double armor, double reward) {
        super(image, r, posX, posY, with, height, health, speed, armor, reward);
    }

    public TankEnemy() {
        this(GameConfig.IMTankEnemy, 0, GameConfig.SPAWNER_DEFAULT_POSX, GameConfig.SPAWNER_DEFAULT_POSY,
                GameConfig.TANKER_ENEMY_WIDTH, GameConfig.TANKER_ENEMY_HEIGHT,
                GameConfig.TANKER_ENEMY_HEALTH, GameConfig.TANKER_ENEMY_SPEED,
                GameConfig.TANKER_ENEMY_ARMOR, GameConfig.TANKER_ENEMY_REWARD);
    }

    public TankEnemy(double posX, double posY) {
        this(GameConfig.IMTankEnemy, 0, posX, posY,
                GameConfig.TANKER_ENEMY_WIDTH, GameConfig.TANKER_ENEMY_HEIGHT,
                GameConfig.TANKER_ENEMY_HEALTH, GameConfig.TANKER_ENEMY_SPEED,
                GameConfig.TANKER_ENEMY_ARMOR, GameConfig.TANKER_ENEMY_REWARD);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {

        super.render(graphicsContext);
        final double percentHealth = health / GameConfig.TANKER_ENEMY_HEALTH;
        if (percentHealth <= 0.25) {
            graphicsContext.setFill(Color.RED);
        } else if (percentHealth <= 0.5) {
            graphicsContext.setFill(Color.ORANGE);
        } else {
            graphicsContext.setFill(Color.LIGHTGREEN);
        }

        graphicsContext.fillRect(this.getPosX(), this.getPosY() + 2,
                width * percentHealth, 2);
    }
}
