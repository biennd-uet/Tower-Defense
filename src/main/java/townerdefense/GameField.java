package townerdefense;

import townerdefense.entity.GameEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameField {
    private List<GameEntity> gameEntities;

    public GameField() {
        this.gameEntities = new ArrayList<>();
    }

    public void removeEntity(GameEntity gameEntity) {
        this.gameEntities.remove(gameEntity);
    }
    public void removeEntity(Collection<GameEntity> gameEntities) {
        this.gameEntities.removeAll(gameEntities);
    }
    public void addEntity(GameEntity gameEntity) {
        this.gameEntities.add(gameEntity);
    }
    public void addEntity(Collection<GameEntity> gameEntities) {
        this.gameEntities.addAll(gameEntities);
    }
}
