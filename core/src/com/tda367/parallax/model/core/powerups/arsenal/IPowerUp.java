package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.powerups.usables.Usable;
import com.tda367.parallax.model.core.util.Transformable;

public interface IPowerUp extends Usable, Collidable {
    void activate(Transformable transformable);

    PowerUpType getPowerUpType();

    boolean isActive();
}
