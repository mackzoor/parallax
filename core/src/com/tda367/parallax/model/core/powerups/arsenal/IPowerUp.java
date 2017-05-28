package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.util.Transformable;
import com.tda367.parallax.model.core.util.Updatable;

public interface IPowerUp extends Collidable, Updatable{
    void activate(Transformable transformable);
    boolean isDead();

    PowerUpType getPowerUpType();

    boolean isActive();
}
