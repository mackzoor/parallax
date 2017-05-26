package com.tda367.parallax.model.core.spacecraft;

import lombok.Getter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class SpaceCraftMobility {
    @Getter
    private float forwardVelocity;
    @Getter
    private float maxPanVelocity;
    @Getter
    private Vector3f pos;
    @Getter
    private Quat4f rot;
    @Getter
    private boolean independentRotation;


    SpaceCraftMobility( Vector3f pos, Quat4f rot, float forwardVelocity, float maxPanVelocity, boolean independentRotation){
        this.pos = pos;
        this.rot = rot;
        this.forwardVelocity = forwardVelocity;
        this.independentRotation = independentRotation;
        this.maxPanVelocity = maxPanVelocity;
    }

}
