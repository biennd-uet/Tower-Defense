package townerdefense.engine.entity;

import townerdefense.engine.entity.bullet.Bullet;

import java.util.Collection;

public interface SpawnableEntity {
    boolean hasEntityToSpawn();

    Entity spawn();
    Collection<Bullet> spawnAll();
}
