package com.tda367.parallax.model.parallaxcore.collision;

/**
 * A class that holds a pair of {@link Collidable} that has collided.
 */

public class CollisionPair {
    private Collidable firstCollidable;
    private Collidable secondCollidable;

    public CollisionPair(Collidable firstCollidable, Collidable secondCollidable) {
        this.firstCollidable = firstCollidable;
        this.secondCollidable = secondCollidable;
    }
}
