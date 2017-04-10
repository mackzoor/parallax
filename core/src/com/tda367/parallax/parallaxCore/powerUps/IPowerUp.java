package com.tda367.parallax.parallaxCore.powerUps;

import com.tda367.parallax.parallaxCore.Usable;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Interface class for powerups in the game.
 */
public interface IPowerUp extends Usable {
    public abstract void usePU(Vector3f pos, Quat4f rot);
}
