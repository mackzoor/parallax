package com.tda367.parallax.model.parallaxcore.collision;

/**
 * Interface for classes that can receive information about collisions.
 */

public interface CollisionObserver {
    void respondToCollision(CollisionPair collisionPair);
}
