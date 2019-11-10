package townerdefense.entity.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import townerdefense.GameConfig;
import townerdefense.GameController;
import townerdefense.entity.Direction;
import townerdefense.entity.Entity;
import townerdefense.entity.MovableEntity;
import townerdefense.entity.UpdateableEntity;
import townerdefense.entity.other.Point;
import townerdefense.entity.tile.map.Map;

public abstract class Enemy extends Entity implements UpdateableEntity, MovableEntity {
    protected double health;
    protected double speed;
    protected double armor;
    protected double reward;
    //Todo: Why not Entity has Image ?
    protected double r;
    private int indexCurrentPoint;
    private Point currentPoint;
    private Image image;

    private Direction direction;

    protected Enemy(Image image, double r, double posX, double posY, double width, double height, double health, double speed, double armor, double reward) {
        super(posX, posY, width, height);
        this.health = health;
        this.speed = speed;
        this.armor = armor;
        this.reward = reward;
        this.image = image;
        this.r = r;
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

        Point nextPoint = this.getNextPoint();
        if(nextPoint == null) {
            return;
        }


        double deltaX = posX - nextPoint.getX();
        double deltaY = posY - nextPoint.getY();
        //System.out.println(deltaX + "      " + deltaY +"       "+ posX + "         " + posY);

        int a = 10;
        if(this.posX < (nextPoint.getX() + a) && this.posX > (nextPoint.getX() - a) &&
                this.posY < (nextPoint.getY() + a) && this.posY > (nextPoint.getY() - a)

        ) {

            currentPoint = this.getNextPoint();
            indexCurrentPoint++;
            int x = (int) ((posX + 30) / GameConfig.SIZE_TILE_WIDTH);
            int y = (int) ((posY + 30) / GameConfig.SIZE_TILE_HEIGHT);

            if(deltaX < 0 && Map.map[y][x] == 2) {
                r = r + 90;
            } else if(deltaY < 0 && Map.map[y][x] == 3) {
                r = r + 90;
            } else if(deltaX > 0 && Map.map[y][x] == 4) {
                r = r - 90;
            } else if(deltaY < 0 && Map.map[y][x] == 5) {
                r = r - 90;
            } else if(deltaY > 0 && Map.map[y][x] == 2) {
                r = r - 90;
            } else if(deltaX > 0 && Map.map[y][x] == 3) {
                r = r - 90;
            } else if(deltaY < 0 && Map.map[y][x] == 4) {
                r = r + 90;
            } else if(deltaX > 0 && Map.map[y][x] == 5) {
                r = r + 90;
            }
            if(r == 360) r = 0;

            //If over the way
            if(currentPoint == null) {
                //Todo Destroy enemy
                return;
            }
        }
        if(deltaX < 0) {
            direction = Direction.RIGHT;


        } else if(deltaY < 0) {
            direction = Direction.DOWN;

        } else if(deltaX > 0) {
            direction = Direction.LEFT;

        } else {
            direction = Direction.UP;

        }
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.save();
        rotate(graphicsContext, r, posX + width / 2, posY + height / 2);
        graphicsContext.drawImage(image, posX, posY, width, height);

        graphicsContext.restore();
    }
}
