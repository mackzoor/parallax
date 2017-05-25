package com.tda367.parallax.model.core.collision;

import com.tda367.parallax.model.core.util.Transformable;

/**
 * Interface for objects with the ability to collide.
 */

public interface Collidable extends Transformable {
    boolean collisionActivated();

    void disableCollision();

    void enableCollision();

    String getCollisionModelPath();

    void addToCollisionManager();

    void removeFromCollisionManager();

    CollidableType getCollidableType();

    void handleCollision(Collidable collidable);
}
