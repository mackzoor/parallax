package com.tda367.parallax.parallaxCore;

import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

/**
 * Created by Anthony on 05/04/2017.
 */
public class Camera implements Updatable{

    private Vector3f pos;
    private Matrix3f rot;

    private float fov;

    private Collidable trackingTarget;

    //TODO implement tracking mode, state pattern?
    /*
    public enum TrackingMode {

        FOLLOW_AND_PAN,FOLLOW_AND_TRACK

    }*/


    public Camera(Vector3f pos, Matrix3f rot, float fov){
        this.pos = pos;
        this.rot = rot;
        this.fov = fov;
    }
    public Camera(){
        this(new Vector3f(0,0,1), new Matrix3f(), 90);
    }

    public void trackTo(Collidable collidable){
        trackingTarget = collidable;
    }
    public void trackMode(){

    }

    public Vector3f getPos() {
        return pos;
    }
    public Matrix3f getRot() {
        return rot;
    }
    public float getFov() {
        return fov;
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        float targetYPos = trackingTarget.getPos().getY();
        pos = new Vector3f(pos.getX(),targetYPos-4, pos.getZ());
    }
}
