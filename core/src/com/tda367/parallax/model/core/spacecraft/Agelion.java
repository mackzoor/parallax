package com.tda367.parallax.model.core.spacecraft;


import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * The spacecraft "Agelion" in the game "Parallax".
 */
public class Agelion extends SpaceCraft {

    private static final String COLLISION_MODEL = "3dModels/agelion/hitbox.obj";
    private static final float MAX_PAN_VELOCITY = 16f;
    private static final int HEALTH = 5;
    private static final SpaceCraftType TYPE = SpaceCraftType.AGELION;

    //Constructors
    Agelion(float forwardVelocity, Vector3f pos, Quat4f rot) {
        super(new SpaceCraftMobility(pos, rot, forwardVelocity, MAX_PAN_VELOCITY, false), HEALTH, TYPE);
    }

    Agelion(float forwardVelocity) {
        this(forwardVelocity, new Vector3f(), new Quat4f());
    }

    Agelion() {
        this(20);
    }

    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL;
    }
}

