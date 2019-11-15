package townerdefense.entity.tile.tower;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import townerdefense.GameConfig;
import townerdefense.GameField;
import townerdefense.entity.DestroyableEntity;
import townerdefense.entity.Entity;
import townerdefense.entity.bullet.Bullet;
import townerdefense.entity.bullet.Laze;
import townerdefense.entity.bullet.NormalBullet;
import townerdefense.entity.enemy.Enemy;
import townerdefense.entity.other.Point;
import townerdefense.entity.tile.SpriteAnimation;

import java.util.function.Predicate;

public class BeamTower extends Tower {
        private double frame_number = 0;
        private double x = 0;
        private double y = 0;
        private boolean reverse = false;

        private boolean UP(Entity enemy){
            if(enemy.getCenterPosX() > posX && enemy.getCenterPosX() < (posX + width) && enemy.getCenterPosY() < getCenterPosY() - height/2)return true;
            else return false;
        }

        private boolean LEFT(Entity enemy){
            if(enemy.getCenterPosY() > posY && enemy.getCenterPosY() < (posY + height) && enemy.getCenterPosX() < getCenterPosX() - width/2)return true;
            else return false;
         }

        private boolean DOWN(Entity enemy){
            if(enemy.getCenterPosX() > posX && enemy.getCenterPosX() < (posX + width) && enemy.getCenterPosY() > getCenterPosY() + height/2)return true;
            else return false;
        }

        private boolean RIGHT(Entity enemy){
            if(enemy.getCenterPosY() > posY && enemy.getCenterPosY() < (posY + height) && enemy.getCenterPosX() > getCenterPosX() + width/2)return true;
            else return false;
        }

        public BeamTower(Image image, double posX, double posY, double width, double height, double speed, double range, double damage) {
            super(image, posX, posY, width, height, speed, range, damage);
        }

        //Default
        public BeamTower() {
            this(GameConfig.IMBeamTower,GameConfig.SIZE_UNIT*9 ,GameConfig.SIZE_UNIT*4 ,
                    GameConfig.TOWER_WIDTH, GameConfig.TOWER_HEIGHT,
                    GameConfig.BEAM_TOWER_SPEED, GameConfig.TOWER_RANGE, GameConfig.TOWER_DAMAGE);
        }
    private void findEnemyInRange() {
        Predicate<Entity> enemyInRange = entity -> UP(entity) || DOWN(entity) ||LEFT(entity) || RIGHT(entity) ;
        GameField.entities.parallelStream()
                .filter(entity -> entity instanceof Enemy)
                .filter(enemy -> ! enemyInRangeQueue.contains(enemy))
                .filter(enemyInRange)
                .forEach(enemy -> this.enemyInRangeQueue.add((Enemy) enemy));
    }

    private void removeEnemyOutRange() {
        Predicate<Entity> enemyOutRange = enemy -> ((DestroyableEntity) enemy).isDestroy() ||
                !(UP(enemy) && DOWN(enemy) &&LEFT(enemy) && RIGHT(enemy));
        this.enemyInRangeQueue.removeIf(enemyOutRange);
    }
    @Override
    public void render(GraphicsContext graphicsContext) {
            super.render(graphicsContext);
        }

    @Override
    public void update(int deltaTime) {
        //super.update(deltaTime);
        this.findEnemyInRange();
        this.removeEnemyOutRange();
        System.out.println(enemyInRangeQueue.size());
        if(!enemyInRangeQueue.isEmpty()){
            if(LEFT(enemyInRangeQueue.peek())){
                theta = -90;
            }else if(RIGHT(enemyInRangeQueue.peek())){
                theta = 90;
            }else if(DOWN(enemyInRangeQueue.peek())){
                theta = 180;
            }else {
                theta = 0;
            }
        }
    }

    @Override
        public Bullet spawn() {
            lastTimeAttack = System.nanoTime();
            return new Laze(enemyInRangeQueue, this.getCenterPosX(), this.getCenterPosY(), GameConfig.TOWER_DAMAGE);
        }

    }


