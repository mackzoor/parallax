package com.tda367.parallax.model.parallaxCore.Collision;

import com.tda367.parallax.model.CoreAbstraction.Collidable;
import java.util.List;

/**
 * Interface for classes that can check if two or more {@link Collidable} have collided.
 */
public interface ICollisionCalculator {

    boolean hasCollided(Collidable first, Collidable second);
    List<CollisionPair> getCollisions(List<? extends Collidable> collidables);
    List<CollisionPair> getCollisions(List<? extends Collidable> firstGroup,List<? extends Collidable> secondGroup);

}
