package com.tda367.parallax.model.core.powerups.container;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.util.Transformable;
import com.tda367.parallax.model.core.util.Updatable;

/**
 * Interface for container classes that hold powerups.
 */
public interface IContainer extends Transformable, Collidable, Updatable {
    IPowerUp getPowerUp();
}
