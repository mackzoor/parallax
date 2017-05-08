package com.tda367.parallax.model.coreabstraction;

/**
 * Interface for objects with the ability to collide.
 */
public interface Collidable extends Transformable {

    boolean isActive();
    void disableCollision();
    void enableCollision();
    Model getCollisionModel();

}
