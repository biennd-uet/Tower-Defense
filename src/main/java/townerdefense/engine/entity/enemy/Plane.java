package townerdefense.engine.entity.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import townerdefense.engine.GameConfig;


public class Plane extends Enemy {
    private double deltaX = GameConfig.TARGET_DEFAULT_POSX - this.posX;
    private double deltaY = GameConfig.TARGET_DEFAULT_POSY - this.posY;
    private double theta = Math.atan2(deltaY, deltaX);


    private Plane(Image image, double r, double posX, double posY, double with, double height, double health, double speed, double armor, double reward) {
        super(image, r, posX, posY, with, height, health, speed, armor, reward);
        this.r = Math.toDegrees(theta);
    }

    public Plane() {
        this(GameConfig.IMPlane, 0, GameConfig.SPAWNER_DEFAULT_POSX, GameConfig.SPAWNER_DEFAULT_POSY,
                GameConfig.SMALLER_ENEMY_WIDTH, GameConfig.SMALLER_ENEMY_HEIGHT,
                GameConfig.SMALLER_ENEMY_HEALTH, GameConfig.SMALLER_ENEMY_SPEED,
                GameConfig.SMALLER_ENEMY_ARMOR, GameConfig.SMALLER_ENEMY_REWARD);
    }

    public Plane(double posX, double posY) {
        this(GameConfig.IMPlane, 0, posX, posY,
                GameConfig.SMALLER_ENEMY_WIDTH, GameConfig.SMALLER_ENEMY_HEIGHT,
                GameConfig.SMALLER_ENEMY_HEALTH, GameConfig.SMALLER_ENEMY_SPEED,
                GameConfig.SMALLER_ENEMY_ARMOR, GameConfig.SMALLER_ENEMY_REWARD);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {

        super.render(graphicsContext);
        final double percentHealth = health / GameConfig.SMALLER_ENEMY_HEALTH;
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

    @Override
    public void update(int deltaTime) {
       if(health > 0){
           double deltaDistance = this.speed * deltaTime / GameConfig.NPS;

           this.posX += deltaDistance * Math.cos(theta);
           this.posY += deltaDistance * Math.sin(theta);

       }
    }
}
