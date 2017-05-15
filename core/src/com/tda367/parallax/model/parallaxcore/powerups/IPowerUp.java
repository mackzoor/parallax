package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.view.Renderable;
import com.tda367.parallax.model.parallaxcore.util.Transformable;

public interface IPowerUp extends Usable, com.tda367.parallax.model.parallaxcore.collision.Collidable {
    void activate(Transformable transformable);
    boolean isActive();
}
