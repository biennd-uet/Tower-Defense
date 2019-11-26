package townerdefense.model;

public class UserManager {
    private static UserManager userManager;

    private int gold;
    private int health;
    private int stage;
    private int turn;
    private boolean hasUpdate;

    private UserManager(int gold, int health, int stage, int turn) {
        this.gold = gold;
        this.health = health;
        this.stage = stage;
        this.turn = turn;
        this.hasUpdate = false;
    }

    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager(100, 20, 1, 0);
        }
        return userManager;
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

    public void nextTurn(int turn) {
        hasUpdate = true;
        this.stage = turn;
    }
    public void lostHealth(int passEnemy){
        hasUpdate = true;
        this.health = 20 - passEnemy;

    }

}
