package townerdefense.entity;

public interface SpawnableEntity {
    boolean hasEntityToSpawn();

    Entity spawn();
}
