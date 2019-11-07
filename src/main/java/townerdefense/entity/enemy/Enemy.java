package townerdefense.entity.enemy;

import townerdefense.GameConfig;
import townerdefense.GameController;
import townerdefense.entity.Direction;
import townerdefense.entity.MovableEntity;
import townerdefense.entity.UpdateableEntity;
import townerdefense.entity.other.Point;
import townerdefense.entity.tile.Tile;

public abstract class Enemy extends Tile implements UpdateableEntity, MovableEntity {
    protected double health;
    protected double speed;
    protected double armor;
    protected double reward;
    private int indexCurrentPoint;
    private Point currentPoint;

    private Direction direction;

    protected Enemy(double posX, double posY, double width, double height, double health, double speed, double armor, double reward) {
        super(posX, posY, width, height);
        this.health = health;
        this.speed = speed;
        this.armor = armor;
        this.reward = reward;
        this.indexCurrentPoint = 0;
        this.currentPoint = GameController.points.get(0);
        this.calcDirection();
    }

    public Point getNextPoint() {
        if(this.indexCurrentPoint < GameController.points.size() - 1) {
            return GameController.points.get(indexCurrentPoint + 1);
        }
        return null;
    }

    @Override
    public void update(int deltaTime) {
        calcDirection();
        final double deltaDistance = speed * deltaTime / GameConfig.NPS;
        switch (direction) {
            case RIGHT:
                posX += deltaDistance;
                break;
            case LEFT:
                posX -= deltaDistance;
                break;
            case DOWN:
                posY += deltaDistance;
                break;
            case UP:
                posY -= deltaDistance;
                break;
            default:
                break;
        }
    }

    private void calcDirection() {
        if (this.posX - currentPoint.getX() > GameConfig.SIZE_TILE_WIDTH ||
        this.posY - currentPoint.getY() > GameConfig.SIZE_TILE_HEIGHT) {
            //System.out.printf("(%f %f) && (%f %f)\n", this.posX, this.posY,
            // this.currentPoint.getX(), this.currentPoint.getY());
            //System.out.printf("%s -> %s\n", currentPoint.toString(), this.getNextPoint().toString());
            currentPoint = this.getNextPoint();
            indexCurrentPoint++;
            //If over the way
            if (currentPoint == null) {
                //Todo Destroy enemy
                return ;
            }
        }
        Point nextPoint = this.getNextPoint();
        if (nextPoint == null) {
            return ;
        }

        final double deltaX = posX - nextPoint.getX();
        final double deltaY = posY - nextPoint.getY();
        //*--X---*----*
        //      |
        //      *
        if (deltaX < 0) {
            direction = Direction.RIGHT;
        } else if (deltaY < 0) {
            direction = Direction.DOWN;
        } else if (deltaX > 0) {
            direction = Direction.LEFT;
        } else {
            direction = Direction.UP;
        }
    }
}
