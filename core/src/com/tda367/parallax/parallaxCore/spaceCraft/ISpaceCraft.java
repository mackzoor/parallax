package com.tda367.parallax.parallaxCore.spaceCraft;

import com.tda367.parallax.parallaxCore.Collidable;
import com.tda367.parallax.parallaxCore.IModel;
import com.tda367.parallax.parallaxCore.Renderable;
import com.tda367.parallax.parallaxCore.course.Course;
import com.tda367.parallax.parallaxCore.powerUps.PowerUp;
import com.tda367.parallax.parallaxCore.Updatable;

import javax.vecmath.Vector2f;

/**
 * Interface for all spaceCraft in the game.
 */
public interface ISpaceCraft extends Collidable, Updatable, Renderable {
    void setSpeedTarget(float speed);
    void setAcceleration(float accelerate);

    void setPanTarget(Vector2f target);
    void addPanTarget(Vector2f target);

    void setPanVelocity(Vector2f velocity);
    void setPanVelocity(float x, float y);
    Vector2f getPanVelocity();
    void addPanVelocity(Vector2f velocity);

    public void action();
    public void setPU(PowerUp pu);

    void addSpaceCraftListener(SpaceCraftListener listener);
}


