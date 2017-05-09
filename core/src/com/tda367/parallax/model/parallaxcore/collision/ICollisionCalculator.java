package com.tda367.parallax.model.parallaxcore.collision;

import java.util.List;

/**
 * Interface for classes that can check if two or more {@link Collidable} have collided.
 */

public interface ICollisionCalculator {

    boolean hasCollided(Collidable first, Collidable second);
    List<CollisionPair> getCollisions(List<? extends Collidable> collidables);
    List<CollisionPair> getCollisions(List<? extends Collidable> firstGroup,List<? extends Collidable> secondGroup);

    void run();
}