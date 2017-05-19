package com.tda367.parallax.model.parallaxcore.spacecraft;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.util.Updatable;
import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import java.util.List;

/**
 * Interface for all spaceCraft in the game.
 */

public interface ISpaceCraft extends Collidable, Updatable {
    void setForwardSpeedTarget(float speed);
    void setForwardAcceleration(float acceleration);

    void setDesiredPanVelocity(Vector2f desiredPanVelocity);
    void setDesiredPanVelocity(float x, float y);

    void action();
    void add(IPowerUp pu);
    void add(List<IPowerUp> listOfPowerUps);

    void incHealth();
    void decHealth();
    int getHealth();
    void setHealth(int health);
    SpaceCraftType getType();

    Vector2f getCurrentPanVelocity();
    void setCurrentPanVelocity(float x, float y);
}


