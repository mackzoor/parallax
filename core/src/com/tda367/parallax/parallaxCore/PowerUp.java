package com.tda367.parallax.parallaxCore;

/**
 * Abstract class for powerups in the game.
 */
public abstract class PowerUp implements Usable{
    abstract void usePU(Vector3D pos, Matrix3D rot);
}
