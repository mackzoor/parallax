package com.tda367.parallax.model.core.spacecraft;

import javax.vecmath.*;

/**
 * The spacecraft "Agelion" in the game "Parallax".
 */
public class Agelion extends SpaceCraft{

    private final static String COLLISION_MODEL = "3dModels/agelion/hitbox.obj";
    private final static float MAX_PAN_VELOCITY = 8f;
    private final static int HEALTH = 5;
    private final static SpaceCraftType TYPE = SpaceCraftType.AGELION;

    //Constructors
    Agelion(float forwardVelocity, Vector3f pos, Quat4f rot) {
        super(HEALTH, forwardVelocity, MAX_PAN_VELOCITY, pos, rot, COLLISION_MODEL, TYPE, false);
    }
    Agelion(float forwardVelocity){
        this(forwardVelocity, new Vector3f(), new Quat4f());
    }
    Agelion(){
        this(20);
    }

    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL;
    }
}

