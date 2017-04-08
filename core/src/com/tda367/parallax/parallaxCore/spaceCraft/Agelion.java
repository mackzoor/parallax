package com.tda367.parallax.parallaxCore.spaceCraft;

import com.tda367.parallax.parallaxCore.PowerUp;

import javax.vecmath.*;

/**
 * Represents the spacecraft in our game.
 */
public class Agelion implements ISpaceCraft {

    private int health; //Current health
    private PowerUp pu; //Current stored power up

    private float velocity;
    private float targetSpeed;
    private float targetAcceleration;
    private boolean speedTargetMode;


    private float panSpeed; // m/s

    private boolean pointMode;
    private Vector2f panTarget;
    private Vector2f velTarget;

    private Vector3f pos;
    private Quat4f rot;


    public Agelion(int health, float velocity, float panSpeed, Vector3f pos, Quat4f rot) {
        this.health = health;
        this.velocity = velocity;
        this.panSpeed = panSpeed;
        this.pos = pos;
        this.rot = rot;

        panTarget = new Vector2f();
        velTarget = new Vector2f();

        this.pointMode = false;
        this.speedTargetMode = true;

    }
    public Agelion(Vector3f position, Quat4f rotation, float startVelocity){
        this(5,startVelocity,2,position,rotation);
    }
    public Agelion(float velocity){
        this(new Vector3f(), new Quat4f(), velocity);
    }
    public Agelion(){
        this(1);
    }


    public synchronized void setSpeedTarget(float speed){
        targetSpeed = speed;
        speedTargetMode = true;
    }
    public synchronized void setAccelerateTarget(float accelerate){
        targetAcceleration = accelerate;
        speedTargetMode = false;
    }

    @Override
    public synchronized void setPanPoint(Vector2f target) {
        panTarget = new Vector2f(target);
        pointMode = true;
    }
    @Override
    public synchronized void addPanPoint(Vector2f target) {
        panTarget.add((Tuple2f) target);
        pointMode = true;
    }
    @Override
    public synchronized void setPanVelocity(Vector2f velocity) {
        velTarget = new Vector2f(velocity);
        pointMode = false;
    }
    @Override
    public synchronized void addPanVelocity(Vector2f velocity) {
        velTarget.add((Tuple2f) velocity);
        pointMode = false;
    }

    private void panCraft(int timeMilli){
        if (pointMode) {
            panPointMode(timeMilli);
        } else {
            panVelocityMode(timeMilli);
        }
    }
    private void accelerateCraft(int timeMilli){
        if (speedTargetMode){
            if (velocity < targetSpeed){
                float speedIncrease = velocity + targetAcceleration * ((float)timeMilli/1000);

                if (targetSpeed < speedIncrease+velocity){
                    velocity = targetSpeed;
                } else {
                    velocity += speedIncrease;
                }

            }
        } else {
            velocity = velocity + targetAcceleration * ((float)timeMilli/1000);
        }
    }
    private void advanceCraft(int timeMilli){
        float posYAdded = velocity * ((float)timeMilli/1000);
        pos.add((Tuple3f)new Vector3f(0, posYAdded, 0));
    }

    private void panPointMode(int timeMilli){
        /*X AXIS */
        float xDiff = panTarget.getX() - pos.getX();
        float xMovement = panSpeed * ((float)timeMilli/1000);
        float posXNew;

        if (xDiff < xMovement){
            posXNew = pos.getX()+xDiff;
        } else {
            posXNew = pos.getX()+xMovement;
        }


        /* Z AXIS */
        float ZDiff = panTarget.getY() - pos.getZ();
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
        float addedXPos = distanceCalc(velTarget.getX(),timeMilli);
        float addedZPos = distanceCalc(velTarget.getY(),timeMilli);

        pos.add((Tuple3f) new Vector3f(addedXPos,0,addedZPos));
    }

    private float distanceCalc(float speed, float timeMilli){
        return speed * ((float)timeMilli/1000);
    }

    public void action(){
        if (pu != null){
            pu.usePU(pos, rot);
        } else {
            System.out.println("NO POWERUP");
        }
    }
    @Override
    public void setPU(PowerUp pu) {
        this.pu = pu;
    }


    //TODO some sort of rotation engine?
    //TODO Spacecraft flight system. (Acc pan etc)

    //TODO Geometry?
    //TODO More?


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
        accelerateCraft(milliSinceLastUpdate);
        panCraft(milliSinceLastUpdate);
        advanceCraft(milliSinceLastUpdate);
    }
}

