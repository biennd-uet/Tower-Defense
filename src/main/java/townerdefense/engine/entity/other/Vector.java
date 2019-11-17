package townerdefense.engine.entity.other;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.engine.entity.DrawableEntity;

public class Vector implements DrawableEntity {
    private final Point A;
    private final Point B;

    public Vector(Point a, Point b) {
        A = a;
        B = b;
    }

    public static void drawLine(double x1, double y1, double x2, double y2, GraphicsContext graphicsContext) {
        graphicsContext.setStroke(Color.RED);
        graphicsContext.strokeLine(x1, y1, x2, y2);
    }

    public double getLength() {
        return Point.getDistance(this.A, this.B);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setStroke(Color.RED);
        graphicsContext.strokeLine(A.getX(), A.getY(), B.getX(), B.getY());
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", this.A.toString(), this.B.toString());
    }
}
