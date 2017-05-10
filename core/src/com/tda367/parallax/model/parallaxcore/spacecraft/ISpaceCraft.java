package com.tda367.parallax.model.parallaxcore.spacecraft;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.util.Renderable;
import com.tda367.parallax.model.util.Updatable;
import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;

import javax.vecmath.Vector2f;
import java.util.List;

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
    void pushPU(IPowerUp pu);
    void pushPU(List<IPowerUp> listOfPowerUps);
    IPowerUp popPU();

    void incHealth();
    void decHealth();
    int getHealth();
}


