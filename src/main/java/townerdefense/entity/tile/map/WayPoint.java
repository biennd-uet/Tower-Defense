package townerdefense.entity.tile.map;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.GameConfig;
import townerdefense.entity.Entity;
import townerdefense.entity.other.Point;
import townerdefense.entity.tile.Map;

import java.util.ArrayList;
import java.util.List;

public class WayPoint extends Entity {
    private List<Point> points;

    //WayPoint sample
    public WayPoint() {
        super(0, 0, 0, 0);
        this.points = new ArrayList<>();
   /*     this.points.add(new Point(0 * GameConfig.SIZE_TILE_WIDTH, 0 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(1 * GameConfig.SIZE_TILE_WIDTH, 0 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(2 * GameConfig.SIZE_TILE_WIDTH, 0 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(3 * GameConfig.SIZE_TILE_WIDTH, 0 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(4 * GameConfig.SIZE_TILE_WIDTH, 0 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(5 * GameConfig.SIZE_TILE_WIDTH, 0 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(6 * GameConfig.SIZE_TILE_WIDTH, 0 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(7 * GameConfig.SIZE_TILE_WIDTH, 0 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(7 * GameConfig.SIZE_TILE_WIDTH, 1 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(7 * GameConfig.SIZE_TILE_WIDTH, 2 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(7 * GameConfig.SIZE_TILE_WIDTH, 3 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(7 * GameConfig.SIZE_TILE_WIDTH, 4 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(7 * GameConfig.SIZE_TILE_WIDTH, 5 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(7 * GameConfig.SIZE_TILE_WIDTH, 6 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(7 * GameConfig.SIZE_TILE_WIDTH, 7 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(7 * GameConfig.SIZE_TILE_WIDTH, 8 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(7 * GameConfig.SIZE_TILE_WIDTH, 9 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(8 * GameConfig.SIZE_TILE_WIDTH, 9 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(9 * GameConfig.SIZE_TILE_WIDTH, 9 * GameConfig.SIZE_TILE_HEIGHT));*/
        this.points.add(new Point(GameConfig.SPAWNER_DEFAULT_POSX, GameConfig.SPAWNER_DEFAULT_POSY));
        this.points.add(new Point(9 * GameConfig.SIZE_TILE_WIDTH, 1 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(9 * GameConfig.SIZE_TILE_WIDTH, 3 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(0 * GameConfig.SIZE_TILE_WIDTH, 3 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(0 * GameConfig.SIZE_TILE_WIDTH, 5 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(9 * GameConfig.SIZE_TILE_WIDTH, 5 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(9 * GameConfig.SIZE_TILE_WIDTH, 7 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(0 * GameConfig.SIZE_TILE_WIDTH, 7 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(0 * GameConfig.SIZE_TILE_WIDTH, 9 * GameConfig.SIZE_TILE_HEIGHT));
        this.points.add(new Point(9 * GameConfig.SIZE_TILE_WIDTH, 9 * GameConfig.SIZE_TILE_HEIGHT));
   /*     while(true){
            int x = GameConfig.SPAWNER_DEFAULT_POSX;
            int y = GameConfig.SPAWNER_DEFAULT_POSY;

        }*/
    }
    //Todo Generate from Map
    public boolean isCanGo(int x,int y,int a,int b){
        if(isExist(x+1,y,a,b)||isExist(x,y+1,a,b)||isExist(x-1,y,a,b)||isExist(x,y-1,a,b))return true;
        else return false;
    }
    public boolean isExist(int x,int y,int a,int b){
        if(x >= a || x < 0|| y >=b ||y < 0)return false;
        else return true;
    }
    public WayPoint(Map map) {
        this();
    }

    public WayPoint(List<Point> points) {
        this();
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        this.points.forEach(point -> point.render(graphicsContext));
    }
}
