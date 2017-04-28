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
    void setForwardSpeedTarget(float speed);
    void setForwardAcceleration(float acceleration);

    void setPanAbsoluteTarget(Vector2f target);
    void offsetAbsolutePanTarget(Vector2f target);

    void setPanAcceleration(Vector2f velocity);
    void setPanAcceleration(float x, float y);
    Vector2f getPanVelocity();

    void action();
    void setPU(PowerUp pu);

    void addSpaceCraftListener(SpaceCraftListener listener);

    void incHealth();
    void decHealth();
    int getHealth();
}


