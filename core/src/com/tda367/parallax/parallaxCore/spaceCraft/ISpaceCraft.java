package com.tda367.parallax.parallaxCore.spaceCraft;

import com.tda367.parallax.CoreAbstraction.Collidable;
import com.tda367.parallax.CoreAbstraction.Renderable;
import com.tda367.parallax.CoreAbstraction.Updatable;
import com.tda367.parallax.parallaxCore.powerUps.IPowerUp;

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

    void setDesiredPanVelocity(Vector2f desiredPanVelocity);
    void setDesiredPanVelocity(float x, float y);

    void action();
    void setPU(IPowerUp pu);

    void addSpaceCraftListener(SpaceCraftListener listener);
    void removeSpaceCraftListener(SpaceCraftListener listener);

    void incHealth();
    void decHealth();
    int getHealth();
}


