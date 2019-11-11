package townerdefense.entity.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import townerdefense.GameConfig;


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
        // graphicsContext.setFill(Color.AQUA);
        // graphicsContext.fillOval(this.getCenterPosX(), this.getCenterPosY(), this.width, this.height);
        super.render(graphicsContext);
    }

    public NormalEnemy() {
        this(GameConfig.SPAWNER_DEFAULT_POSX, GameConfig.SPAWNER_DEFAULT_POSY,
                GameConfig.NORMAL_ENEMY_WIDTH, GameConfig.NORMAL_ENEMY_HEIGHT,
                GameConfig.NORMAL_ENEMY_HEALTH_MAX, GameConfig.NORMAL_ENEMY_SPEED,
                GameConfig.NORMAL_ENEMY_ARMOR, GameConfig.NORMAL_ENEMY_REWARD);
    }*/

    @Override
    public void render(GraphicsContext graphicsContext) {
       // graphicsContext.setFill(Color.AQUA);
       // graphicsContext.fillOval(this.getCenterPosX(), this.getCenterPosY(), this.width, this.height);
        super.render(graphicsContext);
    }
}
