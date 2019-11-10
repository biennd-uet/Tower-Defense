package townerdefense.entity.tile.map;

import javafx.scene.canvas.GraphicsContext;
import townerdefense.GameConfig;
import townerdefense.entity.Entity;
import townerdefense.entity.other.Point;

import java.util.ArrayList;
import java.util.List;

public class WayPoint extends Entity {
    private List<Point> points;

    //WayPoint sample
    public WayPoint() {
        super(0, 0, 0, 0);
        this.points = new ArrayList<>();
        this.points.add(new Point(0 * GameConfig.SIZE_TILE_WIDTH, 0 * GameConfig.SIZE_TILE_HEIGHT));
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
        this.points.add(new Point(9 * GameConfig.SIZE_TILE_WIDTH, 9 * GameConfig.SIZE_TILE_HEIGHT));
    }

    //Todo Generate from Map
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
