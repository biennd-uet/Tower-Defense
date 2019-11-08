package townerdefense.entity.enemy;

import townerdefense.GameConfig;
import townerdefense.GameController;
import townerdefense.entity.*;
import townerdefense.entity.other.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Enemy extends Entity implements UpdateableEntity, MovableEntity {
    protected double health;
    protected double speed;
    protected double armor;
    protected double reward;
    private int indexCurrentPoint;
    private Point currentPoint;
    //Todo: Why not Entity has Image ?
    protected double r;
    private Image image;

    private Direction direction;

    protected Enemy(Image image,double r,double posX, double posY, double width, double height, double health, double speed, double armor, double reward) {
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
        int a = 10;
        if(this.posX < (this.getNextPoint().getX() + a) && this.posX > (this.getNextPoint().getX() - a) &&
           this.posY < (this.getNextPoint().getY() + a) && this.posY > (this.getNextPoint().getY() - a)
                ) {
            //System.out.printf("(%f %f) && (%f %f)\n", this.posX, this.posY,
            // this.currentPoint.getX(), this.currentPoint.getY());
            //System.out.printf("%s -> %s\n", currentPoint.toString(), this.getNextPoint().toString());
            currentPoint = this.getNextPoint();
            indexCurrentPoint++;
            //If over the way
            if(currentPoint == null) {
                //Todo Destroy enemy
                return;
            }
        }
        Point nextPoint = this.getNextPoint();
        if(nextPoint == null) {
            return;
        }



        final double deltaX = posX - nextPoint.getX();
        final double deltaY = posY - nextPoint.getY();
        //*--X---*----*
        //      |
        //      *
        System.out.println(posX + "          " + posY + "        " + currentPoint.getX() +"          "+ currentPoint.getY());
        if(deltaX < 0 ) {
            direction = Direction.RIGHT;
            r = 0;

        } else if(deltaY < 0 ) {
            direction = Direction.DOWN;
            r = 90;
        } else if(deltaX > 0 ) {
            direction = Direction.LEFT;
            r = 180;
        } else {
            direction = Direction.UP;
            r= 270;
        }
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        ImageView a = new ImageView(image);
        a.setRotate(r);
        graphicsContext.drawImage(a.getImage(), posX, posY, this.width, this.height);
    }
}
