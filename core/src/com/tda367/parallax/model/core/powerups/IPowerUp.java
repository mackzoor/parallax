package com.tda367.parallax.model.core.powerups;

import com.tda367.parallax.model.core.collision.Collidable;

public interface IPowerUp extends Usable, Collidable {
    void activate(com.tda367.parallax.model.core.util.Transformable transformable);
    boolean isActive();
}
