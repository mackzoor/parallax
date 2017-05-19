package com.tda367.parallax.model.parallaxcore.spacecraft;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Factory class for creating different types of {@link SpaceCraft}.
 */
public class SpaceCraftFactory {

    public static Agelion getAgelionInstance(float forwardSpeed,Vector3f pos, Quat4f rot){
        return new Agelion(forwardSpeed, pos, rot);
    }

    public static Agelion getAgelionInstance(float forwardSpeed){
        return new Agelion(forwardSpeed);
    }




    public static SpaceCraft getRandom(float forwardSpeed,Vector3f pos, Quat4f rot){
        //TODO When more spaceCrafts are available, return a random one.
        return new Agelion(forwardSpeed, pos, rot);
    }

}
