package com.tda367.parallax.parallaxCore;

/**
 * Interface for powerups in the game.
 */
public interface PowerUp extends Usable{
    void usePU(Vector3D pos, Matrix3D rot);
}
