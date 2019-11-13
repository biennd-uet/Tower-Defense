package townerdefense.entity.tile.tower;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import townerdefense.GameConfig;
import townerdefense.entity.Entity;
import townerdefense.entity.bullet.Bullet;
import townerdefense.entity.tile.SpriteAnimation;

public class NormalTower extends Tower {
    private double frame_number = 0;
    private double x = 0;
    private double y = 0;
    private boolean reverse = false;

    public NormalTower(Image image, double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(image, posX, posY, width, height, speed, range, damage);
    }

    //Default
    public NormalTower() {
        this(GameConfig.IMTower1, GameConfig.TOWER_DEFAULT_POSX, GameConfig.TOWER_DEFAULT_POSY,
                GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                GameConfig.TOWER_SPEED, GameConfig.TOWER_RANGE, GameConfig.TOWER_DAMAGE);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        super.render(graphicsContext);
     /*   if(frame_number > 8)
        {
            frame_number -= 1;
            reverse = true;
        }
        else if(frame_number < 0)
        {
            frame_number += 1;
            reverse = false;
        }

        x = frame_number*(GameConfig.IMAnimationWp.getWidth()/8);

        if(reverse == true)
            frame_number--;
        else
            frame_number++;

        // Clear the canvas


        // Draw next image
        graphicsContext.drawImage(GameConfig.IMAnimationWp, x, y,
                GameConfig.IMAnimationWp.getWidth()/8,
                GameConfig.IMAnimationWp.getHeight(),
                posX, posY,
                width,
                height);

    */
    }

    @Override
    public Bullet spawn() {
        lastTimeAttack = System.nanoTime();
        return new Bullet(enemyInRangeQueue.peek(), this.getCenterPosX(), this.getCenterPosY(), GameConfig.TOWER_DAMAGE);
    }

}
