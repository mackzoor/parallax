package com.tda367.parallax.parallaxCore.spaceCraft;

import com.tda367.parallax.parallaxCore.Model;
import com.tda367.parallax.parallaxCore.RenderManager;
import com.tda367.parallax.parallaxCore.powerUps.Missile;
import com.tda367.parallax.parallaxCore.powerUps.PowerUp;

import javax.vecmath.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the spacecraft in our game.
 */

public class Agelion implements ISpaceCraft {

    //TODO, "private powerUp pu;" when done testing
    private PowerUp pu = new Missile(this); //Current stored power up
    private int health; //Current health

    private float forwardVelocity;
    private float forwardTargetSpeed;
    private float forwardAcceleration;
    private boolean forwardRelativeVelocityMode;

    private float maxPanVelocity;
    private Vector2f panAcceleration;
    private Vector2f currentPanVelocity;
    private Vector2f panAbsoluteTarget;
    private boolean relativePanMode;

    private Vector3f pos;
    private Quat4f rot;

    private Model agelionModel;
    private Model collisionModel;
    private List<SpaceCraftListener> spaceCraftListeners;
    private boolean collisionEnabled;



    //Constructors
    public Agelion(int health, float forwardVelocity, float maxPanVelocity, Vector3f pos, Quat4f rot) {
        this.agelionModel = new Model("agelion.g3db", "3dModels/agelion");
        this.collisionModel = new Model(agelionModel.getModelName(), agelionModel.getModelDirectory());
        this.health = health;
        this.forwardVelocity = forwardVelocity;
        this.maxPanVelocity = maxPanVelocity;
        this.pos = pos;
        this.rot = rot;
        this.panAcceleration = new Vector2f();

        panAbsoluteTarget = new Vector2f();
        currentPanVelocity = new Vector2f();

        this.relativePanMode = false;
        this.forwardRelativeVelocityMode = true;

        collisionEnabled = true;
        spaceCraftListeners = new ArrayList<SpaceCraftListener>();
    }
    public Agelion(Vector3f position, Quat4f rotation, float startVelocity){
        this(5,startVelocity,8,position,rotation);
    }
    public Agelion(float forwardVelocity){
        this(new Vector3f(), new Quat4f(), forwardVelocity);
    }
    public Agelion(){
        this(1);
    }



    //Controls
    @Override
    public synchronized void setPanAcceleration(Vector2f velocity) {
        panAcceleration = new Vector2f(velocity);
        relativePanMode = true;
    }
    @Override
    public void setPanAcceleration(float x, float y) {
        setPanAcceleration(new Vector2f(x, y));
    }
    @Override
    public synchronized void setPanAbsoluteTarget(Vector2f target) {
        panAbsoluteTarget = new Vector2f(target);
        relativePanMode = false;
    }
    @Override
    public synchronized void offsetAbsolutePanTarget(Vector2f target) {
        panAbsoluteTarget.add((Tuple2f) target);
        relativePanMode = false;
    }

    public void setMaxPanVelocity(float panVelocity) {
        this.maxPanVelocity = panVelocity;
    }
    public synchronized void setForwardSpeedTarget(float speed){
        forwardTargetSpeed = speed;
        forwardRelativeVelocityMode = false;
    }
    public synchronized void setForwardAcceleration(float accelerate){
        forwardAcceleration = accelerate;
        forwardRelativeVelocityMode = true;
    }



    //Update
    @Override
    public void update(int milliSinceLastUpdate) {
        accelerateCraft(milliSinceLastUpdate);
        panCraft(milliSinceLastUpdate);
        advanceCraft(milliSinceLastUpdate);
//        System.out.println(pos);
    }

    private void panCraft(int timeMilli){
        if (relativePanMode) {
            panRelativeMode(timeMilli);
        } else {
            panAbsoluteMode(timeMilli);
        }
    }
    private void accelerateCraft(int timeMilli){
        if (forwardRelativeVelocityMode){
            if (forwardVelocity < forwardTargetSpeed){
                float speedIncrease = forwardVelocity + forwardAcceleration * ((float)timeMilli/1000);

                if (forwardTargetSpeed < speedIncrease+ forwardVelocity){
                    forwardVelocity = forwardTargetSpeed;
                } else {
                    forwardVelocity += speedIncrease;
                }

            }
        } else {
            forwardVelocity = forwardVelocity + forwardAcceleration * ((float)timeMilli/1000);
        }
    }
    private void advanceCraft(int timeMilli){
        float posYAdded = forwardVelocity * ((float)timeMilli/1000);
        pos.add((Tuple3f)new Vector3f(0, posYAdded, 0));
    }


    //position calculation
    private void panAbsoluteMode(int timeMilli){
        //Calculate direction vector
        Vector2f targetDirection = new Vector2f(
                panAbsoluteTarget
        );

        targetDirection.sub(new Vector2f(
                pos.getX(), pos.getZ()
        ));

        if (targetDirection.getX() == 0 && targetDirection.getY() == 0){

        } else {
            targetDirection.normalize();
            targetDirection.scale(maxPanVelocity);
            panAcceleration = targetDirection;
            panRelativeMode(timeMilli);
        }

    }
    private void panRelativeMode(int timeMilli){

        //TODO see if the first calculations can also use distanceCalc
        //Update currentPanVelocity with panAcceleration;
        Vector2f addedVelocity = new Vector2f(panAcceleration);
        addedVelocity.scale((float)timeMilli/1000);

        currentPanVelocity.add(addedVelocity);
        currentPanVelocity.clamp(-maxPanVelocity,maxPanVelocity);
        System.out.println(currentPanVelocity);
        //Calculate new position with currentPanVelocity;
        float addedXPos = distanceCalc(currentPanVelocity.getX(),timeMilli);
        float addedZPos = distanceCalc(currentPanVelocity.getY(),timeMilli);

        pos.add((Tuple3f) new Vector3f(addedXPos,0,addedZPos));
    }

    private float distanceCalc(float speed, float timeMilli){
        return speed * ((float)timeMilli/1000);
    }


    //ISpaceCraft
    @Override
    public void addSpaceCraftListener(SpaceCraftListener listener){
        spaceCraftListeners.add(listener);
    }
    @Override
    public void action(){
        if (pu != null){
            pu.usePU(pos, rot);
            for (SpaceCraftListener spaceCraftListener : spaceCraftListeners) {
                spaceCraftListener.powerUPUsed(pu);
            }
        } else {
            System.out.println("NO POWERUP");
        }
    }
    @Override
    public void setPU(PowerUp pu) {
        this.pu = pu;
    }
    @Override
    public void incHealth(){
        health++;
    }
    @Override
    public void decHealth() { health--; }
    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public Vector2f getPanVelocity() {
        return new Vector2f(currentPanVelocity);
    }


    // Getters for testing
    public float getForwardAcceleration() {
        return forwardAcceleration;
    }
    public Vector2f getPanAbsoluteTarget() {
        return panAbsoluteTarget;
    }
    public Vector2f getCurrentPanVelocity() {
        return currentPanVelocity;
    }
    public float getForwardTargetSpeed() {
        return forwardTargetSpeed;
    }
    public float getForwardVelocity() {
        return forwardVelocity;
    }




    //Transformable
    @Override
    public Vector3f getPos() {
        return pos;
    }
    @Override
    public Quat4f getRot() {
        return rot;
    }



    //Render
    @Override
    public void addToRenderManager() {
        RenderManager.getInstance().addRenderTask(this);
    }
    @Override
    public void removeFromRenderManager() {
        RenderManager.getInstance().removeRenderTask(this);
    }
    @Override
    public Model getModel() {
        return agelionModel;
    }



    //Collision
    @Override
    public boolean isActive() {
        return collisionEnabled;
    }
    @Override
    public void disableCollision() {
        collisionEnabled = false;
    }
    @Override
    public void enableCollision() {
        collisionEnabled = true;
    }
    @Override
    public Model getCollisionModel() {
        return collisionModel;
    }
}

