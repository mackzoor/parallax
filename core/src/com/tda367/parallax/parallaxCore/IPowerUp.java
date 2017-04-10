package com.tda367.parallax.parallaxCore;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Abstract class for powerups in the game.
 */
public interface IPowerUp extends Usable{
    public abstract void usePU(Vector3f pos, Quat4f rot);
}
