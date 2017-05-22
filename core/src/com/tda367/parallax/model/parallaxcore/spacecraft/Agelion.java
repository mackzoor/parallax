package com.tda367.parallax.model.parallaxcore.spacecraft;

import javax.vecmath.*;

/**
 * The spacecraft "Agelion" in the game "Parallax".
 */
public class Agelion extends SpaceCraft{

    private final static String collisionModel = "3dModels/agelion/hitbox.obj";
    private final static float maxPanVelocity = 8f;
    private final static int health = 5;
    private final static SpaceCraftType type = SpaceCraftType.AGELION;

    //Constructors
    Agelion(float forwardVelocity, Vector3f pos, Quat4f rot) {
        super(health, forwardVelocity, maxPanVelocity, pos, rot, collisionModel, type);
    }
    Agelion(float forwardVelocity){
        this(forwardVelocity, new Vector3f(), new Quat4f());
    }
    Agelion(){
        this(20);
    }

    @Override
    public String getCollisionModelPath() {
        return collisionModel;
    }
}

