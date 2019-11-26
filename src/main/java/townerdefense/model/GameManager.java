package townerdefense.model;

import javafx.util.Pair;
import townerdefense.engine.GameField;
import townerdefense.engine.entity.tile.Spawner;
import townerdefense.engine.entity.tile.map.Map;
import townerdefense.engine.entity.tile.tower.Tower;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.HashMap;

public class GameManager implements Serializable {

    private GameField gameField;
    private Map map;
    private Spawner spawner;
    private java.util.Map<Pair<Double, Double>, Tower> towerMap;
    private boolean isSelling;
    private boolean isPlaying;

    public GameManager() {
        initSetting();
        initMap();
        initGameField();
    }

    private void initSetting() {
        isPlaying = true;
        System.out.println("SettingManager game...");
    }

    private void initMap() {
        try {
            this.map = new Map();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void initGameField() {
        this.gameField = new GameField();

        this.gameField.addAllEntity(map.getListTile());
        this.spawner = new Spawner(map);
        this.gameField.addEntity(this.spawner);
        this.towerMap = new HashMap<>();
    }

    public boolean isSelling() {
        return isSelling;
    }

    public void setSelling(boolean selling) {
        isSelling = selling;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public java.util.Map<Pair<Double, Double>, Tower> getTowerMap() {
        return towerMap;
    }

    public GameField getGameField() {
        return gameField;
    }

    public Tower getTowerFromTile(double posX, double posY) {
        return this.towerMap.get(new Pair<>(posX, posY));
    }

    public void removeTowerFromTile(double posX, double posY) {
        if (!this.towerMap.containsKey(new Pair<>(posX, posY))) {
            throw new IllegalArgumentException("Has not tower here!");
        }

        gameField.removeEntity(this.towerMap.get(new Pair<>(posX, posY)));
        this.towerMap.remove(new Pair<>(posX, posY));
    }

    public void putTower(double posX, double posY, Tower tower) {
        gameField.addEntity(tower);
        this.towerMap.put(new Pair<>(posX, posY), tower);
    }

    public boolean hasTowerInTile(double posX, double posY) {
        return this.towerMap.containsKey(new Pair<>(posX, posY));
    }

    public Spawner getSpawner() {
        return spawner;
    }

    public Map getMap() {
        return map;
    }

}
