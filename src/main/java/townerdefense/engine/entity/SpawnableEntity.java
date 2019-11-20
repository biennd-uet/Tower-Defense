package townerdefense.engine.entity;

import java.util.Collection;

public interface SpawnableEntity {
    boolean hasEntityToSpawn(int deltaTime);

    boolean hasEntitiesToSpawn(int deltaTime);

    Entity spawn(int deltaTime);

    Collection<? extends Entity> spawnAll(int deltaTime);
}
