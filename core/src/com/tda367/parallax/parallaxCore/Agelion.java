package com.tda367.parallax.parallaxCore;

import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;

/**
 * Represents the spacecraft in our game.
 */
public class Agelion implements ISpaceCraft {

    private int health; //Current health
    private PowerUp pu; //Current stored power up

    private float velocity;
    private float panSpeed; // m/s

    private boolean pointMode;
    private float panXTarget;
    private float panZTarget;

    private float velXTarget;
    private float velZTarget;

    private Vector3f pos;
    private Matrix3f rot;
//    private Vector3D pos; //Position of the craft
//    private Matrix3D rot; //Rotation of the craft

    public Agelion(int health, float velocity, float panSpeed, Vector3f pos, Matrix3f rot) {
        this.health = health;
        this.velocity = velocity;
        this.panSpeed = panSpeed;
        this.pos = pos;
        this.rot = rot;
    }

    public Agelion(Vector3f position, Matrix3f rotation, float startVelocity){
        this.pos = position;
        this.rot = rotation;
        this.velocity = startVelocity;
        this.health = 5;
        this.pu = null;
        this.panSpeed = 2;
        this.pointMode = false;
    }
    public Agelion(){
        this(new Vector3f(), new Matrix3f(), 1);
    }


    public void setSpeedTarget(float speed){
        //TODO implement setSpeedTarget
    }
    public void setAccelerateTarget(float accelerate){
        //TODO implement setAccelerateTarget
    }

    public synchronized void setPanXPoint(float xTarget){
        panXTarget = xTarget;
        pointMode = true;
    }
    public synchronized void setPanZPoint(float zTarget){
        panZTarget = zTarget;
        pointMode = true;
    }

    public void setPanXVelocity(float xVelocity){
        velXTarget = xVelocity;
        pointMode = false;
    }
    public void setPanYVelocity(float yVelocity){
        velZTarget = yVelocity;
        pointMode = false;
    }

    private void panCraft(int timeMilli){
        if (pointMode) {
            panPointMode(timeMilli);
        } else {
            panVelocityMode(timeMilli);
        }
    }
    private void advanceCraft(int timeMilli){
        float posYAdded = velocity * ((float)timeMilli/1000);
        pos.add((Tuple3f)new Vector3f(0, posYAdded, 0));
    }

    private void panPointMode(int timeMilli){
        /*X AXIS */
        float xDiff = panXTarget - pos.getX();
        float xMovement = panSpeed * ((float)timeMilli/1000);
        float posXNew;

        if (xDiff < xMovement){
            posXNew = pos.getX()+xDiff;
        } else {
            posXNew = pos.getX()+xMovement;
        }


        /* Z AXIS */
        float ZDiff = panZTarget - pos.getZ();
        float ZMovement = panSpeed * ((float)timeMilli/1000);
        float posZNew;

        if (ZDiff < ZMovement){
            posZNew = pos.getZ()+ZDiff;
        } else {
            posZNew = pos.getZ()+ZMovement;
        }


        /* Sets new position */
        pos = new Vector3f(posXNew,pos.getY(),posZNew);
    }
    private void panVelocityMode(int timeMilli){
        float addedXPos = distanceCalc(velXTarget,timeMilli);
        float addedZPos = distanceCalc(velZTarget,timeMilli);

        pos.add((Tuple3f) new Vector3f(addedXPos,0,addedZPos));
    }

    private float distanceCalc(float speed, float timeMilli){
        return speed * ((float)timeMilli/1000);
    }

    public void action(){
        pu.usePU(pos, rot);
    }


    //TODO some sort of rotation engine?
    //TODO Spacecraft flight system. (Acc pan etc)


    //TODO Geometry?
    //TODO Turn left right up down? Set speed of movement or set target placement target?
    //TODO Accelerate decelerate? Set thrust target or set speed target?
    //TODO More?


    @Override
    public Vector3f getPos() {
        return pos;
    }
    @Override
    public Matrix3f getRot() {
        return rot;
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        panCraft(milliSinceLastUpdate);
        System.out.println(pos.getX());
        advanceCraft(milliSinceLastUpdate);
    }
}

