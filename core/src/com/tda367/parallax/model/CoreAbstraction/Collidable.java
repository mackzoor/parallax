package com.tda367.parallax.model.CoreAbstraction;

/**
 * Interface for objects with the ability to collide.
 */
public interface Collidable extends Transformable {

    boolean isActive();
    void disableCollision();
    void enableCollision();
    Model getCollisionModel();

}
