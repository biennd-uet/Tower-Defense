package townerdefense.engine.entity.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.enemy.Enemy;

import java.util.Queue;

public class Laze extends Bullet {
    private final double LEFT = posY + GameConfig.STAGE_HEIGHT / GameConfig.NUMBER_TILE_IN_HORIZONTAL;
    private final double RIGHT = posX + GameConfig.STAGE_WIDTH / GameConfig.NUMBER_TILE_IN_HORIZONTAL;
    public boolean isCanRemove = false;
    private double speed;
    private double damage;
    private Queue<Enemy> enemyQueue;
    private double apha;
    private Image image;
    private double frame_number = 0;


    private Laze(Queue<Enemy> enemyQueue, Image image, double posX, double posY, double with, double height, double speed, double damage) {
        super(image, posX, posY, with, height, speed, damage);
        this.enemyQueue = enemyQueue;
    }

    public Laze(Queue<Enemy> enemyQueue, double posX, double posY, double damage) {
        this(enemyQueue, GameConfig.IMBeam, posX, posY,
                GameConfig.BEAM_WIDTH, GameConfig.BEAM_HEIGHT,
                GameConfig.BULLET_SPEED, damage);
    }

    private boolean UP(Enemy enemy) {
        if (enemy.getCenterPosX() > posX && enemy.getCenterPosX() < (posX + GameConfig.TOWER_WIDTH)
                && enemy.getCenterPosY() < getCenterPosY() - GameConfig.TOWER_HEIGHT / 2.0)
            return true;
        else return false;
    }

    private boolean LEFT(Enemy enemy) {
        return enemy.getCenterPosY() > posY && enemy.getCenterPosY() < (posY + GameConfig.TOWER_HEIGHT)
                && enemy.getCenterPosX() < getCenterPosX() - GameConfig.TOWER_WIDTH / 2.0;
    }

    private boolean DOWN(Enemy enemy) {
        return enemy.getCenterPosX() > posX && enemy.getCenterPosX() < (posX + GameConfig.TOWER_WIDTH)
                && enemy.getCenterPosY() > getCenterPosY() + GameConfig.TOWER_HEIGHT / 2.0;
    }

    private boolean RIGHT(Enemy enemy) {
        return enemy.getCenterPosY() > posY && enemy.getCenterPosY() < (posY + GameConfig.TOWER_HEIGHT)
                && enemy.getCenterPosX() > getCenterPosX() + GameConfig.TOWER_WIDTH / 2;
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        if (!enemyQueue.isEmpty()) {
            if (LEFT(enemyQueue.peek())) {
                apha = 180;
            } else if (RIGHT(enemyQueue.peek())) {
                apha = 0;
            } else if (DOWN(enemyQueue.peek())) {
                apha = 90;
            } else if (UP((enemyQueue.peek()))) {
                apha = -90;
            }
            System.out.println(frame_number);
            frame_number = frame_number + 0.4;
            double x = ((int) frame_number) * GameConfig.IMBeam.getWidth() / 10;
            if (frame_number > 10) frame_number = 0;

            graphicsContext.save();
            rotate(graphicsContext, apha, posX, posY);
            double y = 0;
            graphicsContext.drawImage(image, x, y,
                    GameConfig.IMBeam.getWidth() / 10,
                    GameConfig.IMBeam.getHeight(),
                    posX, posY,
                    width, height);
            graphicsContext.restore();
        }

    }

    @Override
    public void update(int deltaTime) {
        //super.update(deltaTime);
        if (!enemyQueue.isEmpty()) {
            if (LEFT(enemyQueue.peek())) {
                posY = LEFT;
            } else if (RIGHT(enemyQueue.peek())) {
                posX = RIGHT;
            } else if (DOWN(enemyQueue.peek())) {
                posX = LEFT;
                posY = RIGHT;
            } else if (UP(enemyQueue.peek())) {

            }
        }


    }

    @Override
    public boolean isDestroy() {
       /* return enemy.isDestroy() || Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                enemy.getCenterPosX(), this.getCenterPosY()) <= GameConfig.SIZE_UNIT;*/
        return frame_number == 0;

    }

    @Override
    public void onDestroy() {
        //  enemy.onAttacked(this.damage);
        if (!enemyQueue.isEmpty()) {
            for (Enemy a : enemyQueue) {
                a.onAttacked(this.damage);
            }
        }

    }
}
