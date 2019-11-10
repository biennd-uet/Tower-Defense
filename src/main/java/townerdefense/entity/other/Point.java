package townerdefense.entity.other;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.entity.DrawableEntity;

public class Point implements DrawableEntity {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double getDistance(Point A, Point B) {
        return Math.hypot(A.x - B.x, A.y - B.y);
    }

    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.hypot(x1 - x2, y1 - y2);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDistance(Point other) {
        return Math.hypot(this.x - other.x, this.y - other.y);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(this.x - 5, this.y - 5, 10, 10);
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", this.x, this.y);
    }
}
