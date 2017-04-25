package com.tda367.parallax.parallaxCore;

/**
 * Created by Anthony on 03/04/2017.
 */
public interface Collidable extends Transformable {

    boolean isActive();
    Model getCollisionModel();

}
