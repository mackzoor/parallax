package com.tda367.parallax.model.parallaxcore.collision;

import com.tda367.parallax.model.coreabstraction.Model;
import com.tda367.parallax.model.coreabstraction.Transformable;

/**
 * Interface for objects with the ability to collide.
 */
public interface Collidable extends Transformable {

    boolean isActive();
    void disableCollision();
    void enableCollision();
    Model getCollisionModel();
    void addToCollisionManager();
    void removeFromCollisionManager();

}
