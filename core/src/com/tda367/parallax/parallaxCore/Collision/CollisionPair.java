package com.tda367.parallax.parallaxCore.Collision;

import com.tda367.parallax.CoreAbstraction.Collidable;

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

    public Collidable getFirstCollidable() {
        return firstCollidable;
    }
    public Collidable getSecondCollidable() {
        return secondCollidable;
    }
}
