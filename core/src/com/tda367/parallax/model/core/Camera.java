package com.tda367.parallax.model.core;

import lombok.Getter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

//TODO implement different tracking modes.

/**
 * A virtual camera with capabilities to track {@link com.tda367.parallax.model.core.util.Transformable}
 */

public class Camera implements com.tda367.parallax.model.core.util.Updatable, com.tda367.parallax.model.core.util.Transformable {

    private Vector3f pos;
    private Quat4f rot;
    @Getter private float fov;

    private com.tda367.parallax.model.core.util.Transformable trackingTarget;

    public Camera(Vector3f pos, Quat4f rot, float fov){
        this.pos = pos;
        this.rot = rot;
        this.fov = fov;
    }

    public Camera(){
        this(new Vector3f(0,0,1), new Quat4f(), 90);
    }


    public void trackTo(com.tda367.parallax.model.core.util.Transformable transformable){
        trackingTarget = transformable;
    }
    public void updatePosition(){
        this.pos.set(trackingTarget.getPos().getX()/ 2,(trackingTarget.getPos().getY()) - 4, trackingTarget.getPos().getZ()/ 2 + 1);
    }


    @Override
    public Vector3f getPos() {
        return pos;
    }
    @Override
    public Quat4f getRot() {
        return rot;
    }


    @Override
    public void update(int milliSinceLastUpdate) {
        updatePosition();
    }
}