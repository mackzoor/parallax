package com.tda367.parallax.parallaxCore.spaceCraft;

import com.tda367.parallax.parallaxCore.Collidable;
import com.tda367.parallax.parallaxCore.IModel;
import com.tda367.parallax.parallaxCore.Renderable;
import com.tda367.parallax.parallaxCore.powerUps.PowerUp;
import com.tda367.parallax.parallaxCore.Updatable;

import javax.vecmath.Vector2f;

/**
 * Interface for all spaceCraft in the game.
 */
public interface ISpaceCraft extends Collidable, Updatable, Renderable {
    void setSpeedTarget(float speed);
    void setAccelerateTarget(float accelerate);

    void setPanPoint(Vector2f target);
    void addPanPoint(Vector2f target);

    void setPanVelocity(Vector2f velocity);
    void addPanVelocity(Vector2f velocity);

    public void action();
    public void setPU(PowerUp pu);
}


