package townerdefense;

import townerdefense.entity.Entity;
import townerdefense.entity.UpdatableEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameField {
    private List<Entity> entities;

    public GameField() {
        this.entities = new ArrayList<>();
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }
    public void removeEntity(Collection<Entity> entities) {
        this.entities.removeAll(entities);
    }
    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }
    public void addEntity(Collection<Entity> entities) {
        this.entities.addAll(entities);
    }
    public final Collection<Entity> getListEntries() {
        return this.entities;
    }

    //Todo update all enemy
    public void updateEnemy(int tick) {
        for(Entity entity: entities) {
            if (entity instanceof UpdatableEntity) {
                ((UpdatableEntity) entity).update(tick);
            }
        }
    }
}
