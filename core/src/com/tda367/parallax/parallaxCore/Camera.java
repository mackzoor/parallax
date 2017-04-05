package com.tda367.parallax.parallaxCore;

/**
 * Created by Anthony on 05/04/2017.
 */
public class Camera implements Updatable{

    private Vector3D pos;
    private Matrix3D rot;

    private float fov;

    private Collidable trackingTarget;

    //TODO implement tracking mode, state pattern?
    /*
    public enum TrackingMode {

        FOLLOW_AND_PAN,FOLLOW_AND_TRACK

    }*/


    public Camera(Vector3D pos, Matrix3D rot, float fov){
        this.pos = pos;
        this.rot = rot;
        this.fov = fov;
    }
    public Camera(){
        this(new Vector3D(0,0,1), new Matrix3D(), 90);
    }

    public void trackTo(Collidable collidable){
        trackingTarget = collidable;
    }
    public void trackMode(){

    }

    public Vector3D getPos() {
        return pos;
    }
    public Matrix3D getRot() {
        return rot;
    }
    public float getFov() {
        return fov;
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        float targetYPos = trackingTarget.getPos().getY();
        System.out.println(pos.getZ());
        pos = new Vector3D(pos.getX(),targetYPos-4, pos.getZ());
    }
}
