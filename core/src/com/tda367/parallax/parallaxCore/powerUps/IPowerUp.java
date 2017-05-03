package com.tda367.parallax.parallaxCore.powerUps;

import com.tda367.parallax.CoreAbstraction.*;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public interface IPowerUp extends Usable, Collidable, Renderable, Updatable, IModel {
    void usePU(Vector3f pos, Quat4f rot);
}
