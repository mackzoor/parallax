package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.util.Renderable;
import com.tda367.parallax.model.util.Transformable;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public interface IPowerUp extends Usable, com.tda367.parallax.model.parallaxcore.collision.Collidable, Renderable {
    void activate(Transformable transformable);
    boolean isActive();
}
