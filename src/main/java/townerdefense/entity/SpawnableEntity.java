package townerdefense.entity;

import java.util.Collection;

public interface SpawnableEntity {
    boolean hasEntityToSpawn();
    Entity spawn();
}
