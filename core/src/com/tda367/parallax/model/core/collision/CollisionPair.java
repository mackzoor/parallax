package com.tda367.parallax.model.core.collision;

import lombok.Getter;

/**
 * A class that holds a pair of {@link Collidable} that has collided.
 */

public class CollisionPair {
    @Getter private Collidable firstCollidable;
    @Getter private Collidable secondCollidable;

    public CollisionPair(Collidable firstCollidable, Collidable secondCollidable) {
        this.firstCollidable = firstCollidable;
        this.secondCollidable = secondCollidable;
    }
}
