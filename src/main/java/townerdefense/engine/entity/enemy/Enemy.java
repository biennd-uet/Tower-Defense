package townerdefense.engine.entity.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import townerdefense.control.GameController;
import townerdefense.engine.GameConfig;
import townerdefense.engine.TypeOfEntity;
import townerdefense.engine.entity.*;
import townerdefense.engine.entity.other.Point;
import townerdefense.engine.entity.tile.map.Map;

public abstract class Enemy extends Entity implements UpdatableEntity, MovableEntity, AttackedableEntity, DestroyableEntity {
    protected double health;
    protected double speed;
    protected double armor;
    protected double reward;
    //Todo: Why not Entity has Image ?
    protected double r;
    private int indexCurrentPoint;
    private Point currentPoint;
    private Image image;

    private double frame_number = 0;
    private double x = 0;
    private double y = 0;
    private boolean dead = false;

    private Direction direction = Direction.RIGHT;

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
       if(health > 0){
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
    }


    private void calcDirection() {

        Point nextPoint = this.getNextPoint();
        if(nextPoint == null) {
            return;
        }

        double deltaX = posX - nextPoint.getX();
        double deltaY = posY - nextPoint.getY();
        //System.out.println(deltaX + "      " + deltaY +"       "+ posX + "         " + posY);

        final int epsilon = 10;
        if(Math.abs(this.posX - this.getNextPoint().getX()) < epsilon &&
                Math.abs(this.posY - this.getNextPoint().getY()) < epsilon) {
            currentPoint = this.getNextPoint();
            indexCurrentPoint++;
            posX = currentPoint.getX();
            posY = currentPoint.getY() ;
            int x = (int) ((posX) / GameConfig.SIZE_TILE_WIDTH);
            int y = (int) ((posY) / GameConfig.SIZE_TILE_HEIGHT);

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
            } else if(deltaX < 0 && Map.map[y][x] == 3) {
                r = r - 90;
            } else if(deltaY > 0 && Map.map[y][x] == 4) {
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
        if(this.health < 0){
            frame_number = frame_number + 0.4;
            if(frame_number < 5){
                x = ((int)frame_number)*GameConfig.IMExplosion.getWidth()/5;
                y = 0;
            }else if(frame_number >= 5 && frame_number < 10){
                x = (((int)frame_number) - 5)*GameConfig.IMExplosion.getWidth()/5;
                y = GameConfig.IMExplosion.getHeight()/3;
            }else{
                x = (((int)frame_number) - 10)*GameConfig.IMExplosion.getWidth()/5;
                y = 2*GameConfig.IMExplosion.getHeight()/3;
            }

            // Clear the canvas


            // Draw next image
            graphicsContext.drawImage(GameConfig.IMExplosion, x, y,
                    GameConfig.IMExplosion.getWidth()/5,
                    GameConfig.IMExplosion.getHeight()/3,
                    posX, posY,
                    width,
                    height);
            if(frame_number > 15){
                frame_number = 0;
                dead = true;
            }
        }


    }

    @Override
    public void onAttacked(double damage) {
        this.health -= damage;
    }

    @Override
    public boolean isDestroy() {
        return dead;
    }

    @Override
    public void onDestroy() {

    }
}
