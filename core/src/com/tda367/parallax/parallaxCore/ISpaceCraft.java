package com.tda367.parallax.parallaxCore;

/**
 * Interface for all spaceCraft in the game.
 */
public interface ISpaceCraft extends Collidable, Updatable {
    void setSpeedTarget(float speed);
    void setAccelerateTarget(float accelerate);

    void setPanXPoint(float xTarget);
    void setPanZPoint(float yTarget);

    void setPanXVelocity(float xAcceleration);
    void setPanYVelocity(float yAcceleration);

    public void action();
}


