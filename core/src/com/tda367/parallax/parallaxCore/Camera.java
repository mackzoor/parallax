package com.tda367.parallax.parallaxCore;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

//TODO implement different tracking modes.

/**
 * A virtual camera with capabilities to track {@link com.tda367.parallax.CoreAbstraction.Transformable}
 */
public class Camera implements com.tda367.parallax.CoreAbstraction.Updatable, com.tda367.parallax.CoreAbstraction.Transformable {

    private Vector3f pos;
    private Quat4f rot;
    private float fov;

    private com.tda367.parallax.CoreAbstraction.Transformable trackingTarget;


    public Camera(Vector3f pos, Quat4f rot, float fov){
        this.pos = pos;
        this.rot = rot;
        this.fov = fov;
    }
    public Camera(){
        this(new Vector3f(0,0,1), new Quat4f(), 90);
    }


    public void trackTo(com.tda367.parallax.CoreAbstraction.Transformable transformable){
        trackingTarget = transformable;
    }
    public void updatePosition(){
        this.pos.set(
                trackingTarget.getPos().getX()/ 2,
                (trackingTarget.getPos().getY()) - 4,
                trackingTarget.getPos().getZ()/ 2 + 1
        );
    }


    public Vector3f getPos() {
        return pos;
    }
    public Quat4f getRot() {
        return rot;
    }
    public float getFov() {
        return fov;
    }


    @Override
    public void update(int milliSinceLastUpdate) {
        updatePosition();
    }
}
