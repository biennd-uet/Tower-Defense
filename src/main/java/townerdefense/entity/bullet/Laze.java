package townerdefense.entity.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import townerdefense.GameConfig;
import townerdefense.entity.DestroyableEntity;
import townerdefense.entity.Entity;
import townerdefense.entity.UpdatableEntity;
import townerdefense.entity.enemy.Enemy;
import townerdefense.entity.other.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Laze extends Bullet {
    public boolean isCanRemove = false;
    private double speed;
    private double damage;
    private Queue<Enemy> enemyQueue ;
    private double apha;
    private Image image;

    private double frame_number = 0;
    private double x = 0;
    private double y = 0;

    private final double LEFT = posY + GameConfig.STAGE_HEIGHT/GameConfig.NUMBER_TILE_IN_HORIZONTAL;
    private final double RIGHT = posX + GameConfig.STAGE_WIDTH/GameConfig.NUMBER_TILE_IN_HORIZONTAL;


    public Laze(Queue<Enemy> enemyQueue,Image image, double posX, double posY, double with, double height, double speed, double damage) {
        super(image, posX, posY, with, height, speed, damage);
        this.enemyQueue = enemyQueue;
    }
    public Laze(Queue<Enemy> enemyQueue, double posX, double posY, double damage){
        this(enemyQueue,GameConfig.IMBeam, posX, posY,
                GameConfig.BEAM_WIDTH, GameConfig.BEAM_HEIGHT,
                GameConfig.BULLET_SPEED, damage);
    }
    @Override
    public void render(GraphicsContext graphicsContext) {
        if(!enemyQueue.isEmpty()){
            if(enemyQueue.peek().getPosX() < posX && Math.abs(enemyQueue.peek().getPosX() - posX) < 10){
                apha = 180;
            }else if(enemyQueue.peek().getPosX() > posX && Math.abs(enemyQueue.peek().getPosX() - posX) < 10){
                apha = 0;
            }else if(enemyQueue.peek().getPosY() > posY && Math.abs(enemyQueue.peek().getPosY() - posY) < 10){
                apha = 90;
            }else if(enemyQueue.peek().getPosY() < posY && Math.abs(enemyQueue.peek().getPosY() - posY) < 10){
                apha = -90;
            }
            //super.render(graphicsContext);
            frame_number = frame_number + 0.4;
            x = ((int)frame_number)*GameConfig.IMBeam.getWidth()/10;
            if(frame_number > 10)frame_number = 0;
            graphicsContext.save();
            rotate(graphicsContext, apha, posX , posY);
            graphicsContext.drawImage(image,x,y,
                    GameConfig.IMBeam.getWidth()/10,
                    GameConfig.IMBeam.getHeight(),
                    posX,posY,
                    width,height);
            graphicsContext.restore();
        }

    }

    @Override
    public void update(int deltaTime) {
        //super.update(deltaTime);
        if(!enemyQueue.isEmpty()){
            if(enemyQueue.peek().getPosX() < posX && Math.abs(enemyQueue.peek().getPosX() - posX) < 10){
                posY = LEFT;
            }else if(enemyQueue.peek().getPosX() > posX && Math.abs(enemyQueue.peek().getPosX() - posX) < 10){
                posX = RIGHT;
            }else if(enemyQueue.peek().getPosY() > posY && Math.abs(enemyQueue.peek().getPosY() - posY) < 10){
                posX = LEFT;
                posY = RIGHT;
            }else if(enemyQueue.peek().getPosY() < posY && Math.abs(enemyQueue.peek().getPosY() - posY) < 10){

            }
        }


    }

    @Override
    public boolean isDestroy() {
       /* return enemy.isDestroy() || Point.getDistance(this.getCenterPosX(), this.getCenterPosY(),
                enemy.getCenterPosX(), this.getCenterPosY()) <= GameConfig.SIZE_UNIT;*/
       if(!enemyQueue.isEmpty()){
           return enemyQueue.peek().isDestroy();
       }else {
           return false;
       }

    }

    @Override
    public void onDestroy() {
        //  enemy.onAttacked(this.damage);
        if(!enemyQueue.isEmpty()){
            for(Enemy a : enemyQueue){
                a.onAttacked(this.damage);
            }
        }

    }
}
