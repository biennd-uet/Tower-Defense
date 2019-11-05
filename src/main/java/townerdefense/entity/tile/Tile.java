package townerdefense.entity.tile;

import townerdefense.entity.Entity;

public abstract class Tile extends Entity implements GameTile {
    protected Tile(double posX, double posY, double with, double height) {
        super(posX, posY, with, height);
    }
}
