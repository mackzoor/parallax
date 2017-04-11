package com.tda367.parallax.parallaxCore.powerUps;

import com.tda367.parallax.parallaxCore.Usable;
import com.tda367.parallax.parallaxCore.spaceCraft.Agelion;
import com.tda367.parallax.parallaxCore.spaceCraft.ISpaceCraft;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Interface class for powerups in the game.
 */
public abstract class PowerUp implements Usable {
    public abstract void usePU(Vector3f pos, Quat4f rot);

    public int hashcode() {
        return this.hashCode() * 31;
    }

    @Override
    public void activate(ISpaceCraft agelion) {
        agelion.setPU(this);
    }

    public boolean equals(PowerUp powerUp) {
        return (powerUp != null && powerUp.equals(this));
    }

}
