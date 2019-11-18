package townerdefense.engine.entity;

import java.util.Collection;

public interface SpawnableEntity {
    boolean hasEntityToSpawn();

    boolean hasEntitiesToSpawn();

    Entity spawn();

    Collection<? extends Entity> spawnAll();
}
