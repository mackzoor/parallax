package com.tda367.parallax.model.parallaxcore.spacecraft;

import com.tda367.parallax.model.coreabstraction.SoundManager;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import lombok.Getter;
import com.tda367.parallax.model.parallaxcore.powerups.Cannon;
import com.tda367.parallax.model.coreabstraction.Model;
import com.tda367.parallax.model.coreabstraction.RenderManager;
import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;

import javax.vecmath.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * The spacecraft "Agelion" in the game "Parallax".
 */

public class Agelion implements ISpaceCraft {

    private List <IPowerUp> pu = new ArrayList <IPowerUp>();
    private int health;

    @Getter protected float forwardVelocity;
    @Getter private float forwardTargetSpeed;
    @Getter private float forwardAcceleration;
    private boolean forwardRelativeVelocityMode;

    private float maxPanVelocity;
    private boolean relativePanMode;
    private Vector2f desiredPanVelocity;
    private Vector2f panAcceleration;
    @Getter private Vector2f currentPanVelocity;
    @Getter private Vector2f panAbsoluteTarget;

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
        this.desiredPanVelocity = new Vector2f();
        this.pu = new ArrayList<IPowerUp>();

        //Debug to add 3 cannon shots at spawn.
        List<IPowerUp> addedPus = new ArrayList<IPowerUp>();
        for (int i = 0; i < 3; i++){
            addedPus.add(new Cannon());
        }
        pushPU(addedPus);

        panAbsoluteTarget = new Vector2f();
        currentPanVelocity = new Vector2f();

        this.relativePanMode = true;
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
    public void setDesiredPanVelocity(Vector2f desiredPanVelocity) {
        this.desiredPanVelocity = desiredPanVelocity;
        relativePanMode = true;
    }
    @Override
    public void setDesiredPanVelocity(float x, float y){
        this.setDesiredPanVelocity(new Vector2f(x,y));
    }
    @Override
    public synchronized void setPanAcceleration(Vector2f velocity) {
        panAcceleration = velocity;
        relativePanMode = true;
    }
    @Override
    public synchronized void setPanAbsoluteTarget(Vector2f target) {
        panAbsoluteTarget = new Vector2f(target);
        relativePanMode = false;
    }
    @Override
    public synchronized void offsetAbsolutePanTarget(Vector2f target) {
        panAbsoluteTarget.add( target);
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
    public void update(int milliSinceLastUpdate){
        accelerateCraft(milliSinceLastUpdate);
        updatePanAcceleration();
        panCraft(milliSinceLastUpdate);
        advanceCraft(milliSinceLastUpdate);
    }
    private void updatePanAcceleration(){
        Vector2f truePanVector = new Vector2f(desiredPanVelocity);
        truePanVector.scale(maxPanVelocity);

        truePanVector.sub( currentPanVelocity);
        truePanVector.scale(maxPanVelocity);

        panAcceleration = new Vector2f(truePanVector);
    }
    private void panCraft(int timeMilli){
        if (relativePanMode) {
            panRelativeMode(timeMilli);
        } else {
            panAbsoluteMode(timeMilli);
        }
    }


    //Position calculation
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
            desiredPanVelocity = targetDirection;
            panRelativeMode(timeMilli);
        }

    }
    private void panRelativeMode(int timeMilli){
        Vector2f addedVelocity = new Vector2f(panAcceleration);
        addedVelocity.scale((float)timeMilli/1000);

        currentPanVelocity.add(addedVelocity);
        currentPanVelocity.clamp(-maxPanVelocity,maxPanVelocity);


        float addedXPos = distanceCalc(currentPanVelocity.getX(),timeMilli);
        float addedZPos = distanceCalc(currentPanVelocity.getY(),timeMilli);

        pos.add(new Vector3f(addedXPos,0,addedZPos));
    }
    private float distanceCalc(float speed, float timeMilli){
        return speed * (timeMilli/1000);
    }
    private void accelerateCraft(int timeMilli){
        if (forwardRelativeVelocityMode){
            forwardVelocity = forwardVelocity + forwardAcceleration * ((float)timeMilli/1000);
        } else {
            if (forwardVelocity < forwardTargetSpeed){
                float speedIncrease = forwardVelocity + forwardAcceleration * ((float)timeMilli/1000);

                if (forwardTargetSpeed < speedIncrease+ forwardVelocity){
                    forwardVelocity = forwardTargetSpeed;
                } else {
                    forwardVelocity += speedIncrease;
                }

            }
        }
    }
    private void advanceCraft(int timeMilli){
        float posYAdded = forwardVelocity * ((float)timeMilli/1000);
        pos.add(new Vector3f(0, posYAdded, 0));
    }


    //ISpaceCraft
    @Override
    public void addSpaceCraftListener(SpaceCraftListener listener){
        spaceCraftListeners.add(listener);
    }
    @Override
    public void removeSpaceCraftListener(SpaceCraftListener listener) {
        spaceCraftListeners.remove(listener);
    }
    @Override
    public void action(){
        if (!pu.isEmpty()){
            IPowerUp activePowerUP = pu.get(pu.size()-1);

            activePowerUP.usePU(pos,rot);

            for (SpaceCraftListener spaceCraftListener : spaceCraftListeners) {
                spaceCraftListener.powerUPUsed(pu.get(pu.size()-1));
                pu.remove(pu.size()-1);
            }
        } else {
            System.out.println("NO POWERUP");
        }
    }
    @Override
    public void pushPU(IPowerUp singlePu) {
        //Adds a single powerUp in a list if empty or only if the other powerups in it are the same type of powerUps
        if (pu.size() <= 0){
            this.pu.add(singlePu);
        } else if (
                pu.get(0).getClass().equals(singlePu.getClass())
                && pu.size() < 4
                ){
            this.pu.add(singlePu);
        }
    }
    @Override
    public void pushPU(List<IPowerUp> listOfPowerUps){
        for (IPowerUp pu : listOfPowerUps){
            pushPU(pu);
        }
    }
    @Override
    public IPowerUp popPU() throws EmptyStackException{
        //Pops a PowerUp if the list isn't empty, otherwise throws exception.
        if(this.pu.isEmpty()) throw new EmptyStackException();

        IPowerUp singlePU = this.pu.get(this.pu.size()-1);
        this.pu.remove(singlePU);

        return singlePU;
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


    //Collision
    @Override
    public boolean collisionActivated() {
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
    @Override
    public void addToCollisionManager() {
        CollisionManager.getInstance().addCollisionCheck(this);
    }
    @Override
    public void removeFromCollisionManager() {
        CollisionManager.getInstance().addCollisionCheck(this);
    }
    @Override
    public CollidableType getCollidableType() {
        return CollidableType.SPACECRAFT;
    }
    @Override
    public void handleCollision(CollidableType type) {
        if (type == CollidableType.OBSTACLE || type == CollidableType.HARMFUL){
            SoundManager.getInstance().playSound("flashBang.mp3","sounds/effects", 0.2f);
            decHealth();
        }
    }

    @Override
    public Model getModel() {
        return agelionModel;
    }
}

