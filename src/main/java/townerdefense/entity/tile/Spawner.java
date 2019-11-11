package townerdefense.entity.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import townerdefense.GameConfig;
import townerdefense.entity.Entity;
import townerdefense.entity.SpawnableEntity;
import townerdefense.entity.UpdateableEntity;

public class Spawner extends Tile implements UpdateableEntity, SpawnableEntity {

    public Spawner(double posX, double posY, double with, double height) {
        super(posX, posY, with, height);
    }

    public Spawner() {
        this(GameConfig.SPAWNER_DEFAULT_POSX, GameConfig.SPAWNER_DEFAULT_POSY,
                GameConfig.SPAWNER_WIDTH, GameConfig.SPAWNER_HEIGHT);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.LIGHTGREEN);
        graphicsContext.fillOval(this.posX, this.posY, this.width, this.height);
        //System.out.printf("%f %f %f %f\n", this.posX, this.posY, this.with, this.height);
    }

    @Override
    public void update(int deltaTime) {

    }

    @Override
    public Entity spawn() {
        return null;
    }

    @Override
    public boolean hasEntityToSpawn() {
        return false;
    }
}
