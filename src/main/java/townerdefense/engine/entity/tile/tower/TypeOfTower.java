package townerdefense.engine.entity.tile.tower;

import javafx.scene.image.Image;
import townerdefense.engine.GameConfig;

import java.util.HashMap;
import java.util.Map;

public enum TypeOfTower {
    NormalTower(GameConfig.IMTower1, GameConfig.TOWER_RANGE, GameConfig.TOWER_PRICE, GameConfig.TOWER_DAMAGE),
    RocketTower(GameConfig.IMRocketTower, GameConfig.ROCKET_TOWER_RANGE, GameConfig.ROCKET_TOWER_PRICE, GameConfig.ROCKET_TOWER_DAMAGE),
    BeamTower(GameConfig.IMBeamTower, GameConfig.BEAM_TOWER_RANGE, GameConfig.BEAM_TOWER_PRICE, GameConfig.BEAM_TOWER_DAMAGE),
    MachineGunTower(GameConfig.IMMachineGunTower, GameConfig.MACHINE_GUN_TOWER_RANGE, GameConfig.MACHINE_GUN_TOWER_PRICE, GameConfig.MACHINE_GUN_TOWER_DAMAGE);

    private static Map<Class<? extends Tower>, TypeOfTower> classTypeOfEntityMap;

    static {
        classTypeOfEntityMap = new HashMap<>();
        classTypeOfEntityMap.put(townerdefense.engine.entity.tile.tower.NormalTower.class, TypeOfTower.NormalTower);
        classTypeOfEntityMap.put(townerdefense.engine.entity.tile.tower.RocketTower.class, TypeOfTower.RocketTower);
        classTypeOfEntityMap.put(townerdefense.engine.entity.tile.tower.BeamTower.class, TypeOfTower.BeamTower);
        classTypeOfEntityMap.put(townerdefense.engine.entity.tile.tower.MachineGunTower.class, TypeOfTower.MachineGunTower);
    }

    private Image image;
    private double range;
    private int price;
    private int damage;

    TypeOfTower(Image image, double range, int price, int damage) {
        this.image = image;
        this.range = range;
        this.price = price;
        this.damage = damage;
    }

    public static TypeOfTower getTypeOfTowerByClass(Tower tower) {
        return classTypeOfEntityMap.get(tower.getClass());
    }

    public Image getImage() {
        return image;
    }

    public double getRange() {
        return range;
    }

    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }
}
