package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.coreabstraction.*;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public interface IPowerUp extends Usable, com.tda367.parallax.model.parallaxcore.collision.Collidable, Renderable, Updatable, IModel {
    void usePU(Vector3f pos, Quat4f rot);
}
