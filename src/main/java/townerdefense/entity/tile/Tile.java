package townerdefense.entity.tile;


import townerdefense.entity.Entity;

public abstract class Tile extends Entity implements GameTile {
    public Tile(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
    }
}
