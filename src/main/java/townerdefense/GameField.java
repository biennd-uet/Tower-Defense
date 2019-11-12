package townerdefense;

import townerdefense.entity.DestroyableEntity;
import townerdefense.entity.Entity;
import townerdefense.entity.SpawnableEntity;
import townerdefense.entity.UpdatableEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameField {
    public static List<Entity> entities;
    private List<Entity> spawnedEntity = new ArrayList<>();
    private List<Entity> destroyEntity = new ArrayList<>();

    public GameField() {
        this.entities = new ArrayList<>();
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void removeAllEntity(Collection<Entity> entities) {
        GameField.entities.removeAll(entities);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addAllEntity(Collection<Entity> entities) {
        GameField.entities.addAll(entities);
    }

    public final Collection<Entity> getListEntries() {
        return entities;
    }

    //Todo update all enemy
    public void updateEnemy(int deltaTime) {
        //Step by step update game field
        //1. Update state
        //2. Update destroyable entity
        //3. Update spawnalbe entity


        //Update state
        GameField.entities.forEach(entity -> {
            if(entity instanceof UpdatableEntity) {
                ((UpdatableEntity) entity).update(deltaTime);
            }
        });
        //Update destroyable entity
        GameField.entities.forEach(entity -> {
            if(entity instanceof DestroyableEntity && ((DestroyableEntity) entity).isDestroy()) {
                destroyEntity.add(entity);
            }
        });

        destroyEntity.forEach(entity -> ((DestroyableEntity) entity).onDestroy());
        GameField.entities.removeAll(destroyEntity);
        destroyEntity.clear();
        //Update spawnalbe entity
        GameField.entities.forEach(entity -> {
            if(entity instanceof SpawnableEntity &&
                    ((SpawnableEntity) entity).hasEntityToSpawn()) {
                spawnedEntity.add(((SpawnableEntity) entity).spawn());
            }
        });
        //Add them to game field
        GameField.entities.addAll(spawnedEntity);
        spawnedEntity.clear();
    }
}
