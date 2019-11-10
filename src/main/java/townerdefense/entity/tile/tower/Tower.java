package townerdefense.entity.tile.tower;

import javafx.scene.image.Image;
import townerdefense.entity.UpdateableEntity;
import townerdefense.entity.tile.Tile;

public abstract class Tower extends Tile implements UpdateableEntity {
    private double speed;
    private double range;
    private double damage;
    private Image image;

    public Tower(Image image,double posX, double posY, double width, double height, double speed, double range, double damage) {
        super(posX, posY, width, height);
        this.image = image;
        this.speed = speed;
        this.range = range;
        this.damage = damage;
    }
}
