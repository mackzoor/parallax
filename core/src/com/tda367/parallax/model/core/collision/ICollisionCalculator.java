package com.tda367.parallax.model.core.collision;

import java.util.List;

/**
 * Interface for classes that can check if two or more {@link Collidable} have collided.
 */

public interface ICollisionCalculator {

    CollisionResult checkCollision(Collidable first, Collidable second);

    List<CollisionResult> getAllCollisions(List<? extends Collidable> collidables);

    void run();
}
