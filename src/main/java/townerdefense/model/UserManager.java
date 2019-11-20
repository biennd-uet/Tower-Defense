package townerdefense.model;

import javafx.beans.value.ObservableValue;

public class UserManager {
    private int gold;
    private int health;
    private int stage;
    private int turn;
    private boolean hasUpdate;

    public UserManager(int gold, int health, int stage, int turn) {
        this.gold = gold;
        this.health = health;
        this.stage = stage;
        this.turn = turn;
        this.hasUpdate = false;
    }

    public int getGold() {
        return gold;
    }

    public int getTurn() {
        return turn;
    }

    public int getHealth() {
        return health;
    }

    public int getStage() {

        return stage;
    }

    public void getReward(int reward) {
        this.gold += reward;
    }

    public boolean canBuyTower(int price) {
        return price <= this.gold;
    }

    public void buyTower(int price) {
        hasUpdate = true;
        if (!canBuyTower(price)) {
            throw new IllegalArgumentException("Don't have enough gold!");
        }
        this.gold -= price;
    }

    public boolean hasUpdate() {
        return this.hasUpdate;
    }

    public void doUpdate() {

    }
}
