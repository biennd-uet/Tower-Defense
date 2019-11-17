package townerdefense.engine.entity;

public interface SpawnableEntity {
    boolean hasEntityToSpawn();

    Entity spawn();
}
