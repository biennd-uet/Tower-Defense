package townerdefense.engine.entity.bullet;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.enemy.Enemy;

public class NormalBullet extends Bullet {

    private NormalBullet(Enemy enemy, double posX, double posY, double with, double height, double speed, double damage) {
        super(enemy, posX, posY,
                with, height,
                speed, damage);
    }

    public NormalBullet(Enemy enemy, double posX, double posY, double damage) {
        this(enemy, posX, posY,
                GameConfig.BULLET_WIDTH, GameConfig.BULLET_HEIGHT,
                GameConfig.BULLET_SPEED, damage);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.save();
        rotate(graphicsContext, alpha, posX + width / 2, posY + height / 2);
        graphicsContext.drawImage(GameConfig.IMBullet, posX, posY, width, height);

        graphicsContext.restore();
    }

    @Override
    public void update(int deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public boolean isDestroy() {
        return super.isDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
