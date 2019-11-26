package townerdefense.engine.entity.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.tile.map.Map;


public class BossEnemy extends Enemy {
    private BossEnemy(double r, double posX, double posY, double with, double height, double health, double speed, double armor, double reward, Map map) {
        super(r, posX, posY, with, height, health, speed, map);
    }

    public BossEnemy(double posX, double posY, Map map) {
        this(0, posX, posY,
                GameConfig.BOSS_ENEMY_WIDTH, GameConfig.BOSS_ENEMY_HEIGHT,
                GameConfig.BOSS_ENEMY_HEALTH, GameConfig.BOSS_ENEMY_SPEED,
                GameConfig.BOSS_ENEMY_ARMOR, GameConfig.BOSS_ENEMY_REWARD,
                map);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.save();
        rotate(graphicsContext, r, posX + width / 2, posY + height / 2);
        graphicsContext.drawImage(GameConfig.IMBoss, this.getCenterPosX() - width / 2, this.getCenterPosY() - height / 2, width, height);

        graphicsContext.restore();
        super.render(graphicsContext);
        final double percentHealth = health / GameConfig.BOSS_ENEMY_HEALTH;
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
