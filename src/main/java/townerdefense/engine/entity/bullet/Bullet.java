package townerdefense.engine.entity.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import townerdefense.engine.GameConfig;
import townerdefense.engine.entity.DestroyableEntity;
import townerdefense.engine.entity.Entity;
import townerdefense.engine.entity.UpdatableEntity;
import townerdefense.engine.entity.enemy.Enemy;
import townerdefense.engine.entity.other.Point;

public abstract class Bullet extends Entity implements UpdatableEntity, DestroyableEntity {
    public boolean isCanRemove = false;
    private double speed;
    private double damage;
    private Enemy enemy;
    private double theta = 0;
    private double apha;
    private Image image;

    public Bullet(Enemy enemy, Image image, double posX, double posY, double with, double height, double speed, double damage) {
        super(posX, posY, with, height);
        this.speed = speed;
        this.damage = damage;
        this.enemy = enemy;
        this.image = image;
    }

    public Bullet(Image image, double posX, double posY, double with, double height, double speed, double damage) {
        super(posX, posY, with, height);
        this.image = image;
        this.speed = speed;
        this.damage = damage;
    }

    public void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        //   graphicsContext.setFill(Color.AQUA);
        //   graphicsContext.fillOval(this.getCenterPosX(), this.getCenterPosY(), width, height);
        graphicsContext.save();
        rotate(graphicsContext, apha, posX + width / 2, posY + height / 2);
        graphicsContext.drawImage(image, posX, posY, width, height);

        graphicsContext.restore();
    }

    @Override
    public void update(int deltaTime) {
        double deltaDistance = this.speed * deltaTime / GameConfig.NPS;
        if (!enemy.isDead()) {

            double deltaX = enemy.getCenterPosX() - this.posX;
            double deltaY = enemy.getCenterPosY() - this.posY;
            theta = Math.atan2(deltaY, deltaX);
            apha = Math.toDegrees(Math.PI / 2 + theta);
            this.posX += deltaDistance * Math.cos(theta);
            this.posY += deltaDistance * Math.sin(theta);
        } else {
            this.posX += deltaDistance * Math.cos(theta);
            this.posY += deltaDistance * Math.sin(theta);
        }

    }

    @Override
    public boolean isDestroy() {
        return this.getCenterPosX() > GameConfig.STAGE_WIDTH || this.getCenterPosX() < 0 || this.getCenterPosY() > GameConfig.STAGE_HEIGHT || this.getCenterPosY() < 0
                || Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                enemy.getCenterPosX(), this.getCenterPosY()) <= 10;
    }

    @Override
    public void onDestroy() {
        enemy.onAttacked(this.damage);
    }
}
