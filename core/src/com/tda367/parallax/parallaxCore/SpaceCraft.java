package com.tda367.parallax.parallaxCore;

/**
 * Interface for all spaceCraft in the game.
 */
public interface SpaceCraft extends Collidable, Updatable {

    //  Speed   //
    void setSpeedTarget(float speed);
    void setAccelerateTarget(float accelerate);

    // Pan Y&X //
    void setPanXPoint(float xTarget);
    void setPanZPoint(float yTarget);

    void setPanXVelocity(float xAcceleration);
    void setPanYVelocity(float yAcceleration);

    //  Action  //
    public void action();
}


