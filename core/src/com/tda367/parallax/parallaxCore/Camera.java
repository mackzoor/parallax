package com.tda367.parallax.parallaxCore;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by Anthony on 05/04/2017.
 */
public class Camera implements Updatable, Transformable{

    private Vector3f pos;
    private Quat4f rot;

    private float fov;

    private Collidable trackingTarget;

    //TODO implement tracking mode, state pattern?
    /*
    public enum TrackingMode {

        FOLLOW_AND_PAN,FOLLOW_AND_TRACK

    }*/


    public Camera(Vector3f pos, Quat4f rot, float fov){
        this.pos = pos;
        this.rot = rot;
        this.fov = fov;
    }
    public Camera(){
        this(new Vector3f(0,0,1), new Quat4f(), 90);
    }

    public void trackTo(Collidable collidable){
        trackingTarget = collidable;
    }
    public void updatePosition(){

        //Updates camera
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
//        float targetYPos = trackingTarget.getPos().getY();
//        pos = new Vector3f(pos.getX(),targetYPos-4, pos.getZ());
    }
}
