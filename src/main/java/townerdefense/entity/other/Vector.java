package townerdefense.entity.other;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.entity.DrawableEntity;

public class Vector implements DrawableEntity {
    private final Point A;
    private final Point B;

    public Vector(Point a, Point b) {
        A = a;
        B = b;
    }

    public static Vector getVector(Point A, Point B) {
        return new Vector(A, B);
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
