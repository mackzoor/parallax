package com.tda367.parallax.model.parallaxcore.spacecraft;

import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.model.parallaxcore.powerups.IContainer;
import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for all spacecraft.
 */
public abstract class SpaceCraft implements ISpaceCraft{


    @Getter private List<IPowerUp> pu = new ArrayList<IPowerUp>();
    @Setter @Getter private int health;

    @Getter private float forwardVelocity;
    @Getter private float forwardTargetSpeed;
    @Getter private float forwardAcceleration;
    private boolean forwardRelativeVelocityMode;

    private float maxPanVelocity;
    private Vector2f desiredPanVelocity;
    private Vector2f panAcceleration;
    @Setter @Getter private Vector2f currentPanVelocity;
    @Getter private Vector2f panAbsoluteTarget;

    @Getter private Vector3f pos;
    @Getter private Quat4f rot;

    private String collisionModel;
    private boolean collisionEnabled;
    private SpaceCraftType type;

    //SpaceCraft movement limiter
    private final float courseRadius = 3;

    SpaceCraft(int health, float forwardVelocity, float maxPanVelocity, Vector3f pos, Quat4f rot, String pathToCollisionModel, SpaceCraftType type) {
        this.collisionModel = pathToCollisionModel;
        this.health = health;
        this.type = type;
        this.forwardVelocity = forwardVelocity;
        this.maxPanVelocity = maxPanVelocity;
        this.pos = pos;
        this.rot = rot;
        this.panAcceleration = new Vector2f();
        this.desiredPanVelocity = new Vector2f();
        this.pu = new ArrayList<IPowerUp>();

        panAbsoluteTarget = new Vector2f();
        currentPanVelocity = new Vector2f();

        this.forwardRelativeVelocityMode = true;

        collisionEnabled = true;
    }


    //Controls

    @Override
    public void setDesiredPanVelocity(float x, float y){
        this.setDesiredPanVelocity(new Vector2f(x,y));
    }
    public void setMaxPanVelocity(float panVelocity) {
        this.maxPanVelocity = panVelocity;
    }
    @Override
    public synchronized void setForwardSpeedTarget(float speed){
        forwardTargetSpeed = speed;
        forwardRelativeVelocityMode = false;
    }
    @Override
    public synchronized void setForwardAcceleration(float accelerate){
        forwardAcceleration = accelerate;
        forwardRelativeVelocityMode = true;
    }


    //Update
    @Override
    public void update(int milliSinceLastUpdate){
        accelerateCraft(milliSinceLastUpdate);
        updatePanAcceleration();
        panRelativeMode(milliSinceLastUpdate);
        advanceCraft(milliSinceLastUpdate);
        outOfBoundsCheck();
    }


    @Override
    public void setDesiredPanVelocity(Vector2f desiredPanVelocity){
        if (isShipOutsideCourse()){
            this.desiredPanVelocity.set(onWallSteering(desiredPanVelocity));
        } else {
            this.desiredPanVelocity.set(desiredPanVelocity);
        }
    }

    //SpaceCraft movement limiter
    private Vector2f xzPos(Vector3f vector3f) {
        return new Vector2f(vector3f.getX(), vector3f.getZ());
    }
    private boolean isShipOutsideCourse() {
//        Vector2f vector2f = xzPos(getPos());
//        return (vector2f.length() > courseRadius);
        return false;
    }
    private Vector2f rotateNinetyDeg(Vector2f vector2f) {
        return new Vector2f(-vector2f.getY(),vector2f.getX());
    }
    private Vector2f onWallSteering(Vector2f desiredPanVelocity) {
        Vector2f alongWallVec = rotateNinetyDeg(xzPos(getPos()));
        float scalar = desiredPanVelocity.dot(alongWallVec)/alongWallVec.lengthSquared();
        desiredPanVelocity.scale(scalar, alongWallVec);
        return desiredPanVelocity;
    }
    private void outOfBoundsCheck() {
        if (isShipOutsideCourse()) {
            Vector2f tempVec = xzPos(getPos());
            tempVec.normalize();
            tempVec.scale((49f/50f) * courseRadius); //Too compensate for rounding of float
            getPos().set(new Vector3f(tempVec.getX(), getPos().getY(), tempVec.getY()));
        }
    }

    //Movement related methods
    private void updatePanAcceleration(){
        Vector2f truePanVector = new Vector2f(desiredPanVelocity);
        truePanVector.scale(maxPanVelocity);

        truePanVector.sub( currentPanVelocity);
        truePanVector.scale(maxPanVelocity);

        panAcceleration = new Vector2f(truePanVector);
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

    @Override
    public void setCurrentPanVelocity(float x, float y) {
        currentPanVelocity.x = x;
        currentPanVelocity.y = y;
    }
    public void setCurrentPanVelocity(Vector2f currentPanVelocity) {
        this.currentPanVelocity = currentPanVelocity;
    }


    //ISpaceCraft
    @Override
    public void action(){
        if (!pu.isEmpty()){
            IPowerUp activePowerUP = pu.get(pu.size()-1);

            activePowerUP.activate(this);
            pu.remove(pu.size()-1);
        } else {
            System.out.println("NO POWERUP");
        }
    }
    @Override
    public void add(IPowerUp singlePu) {
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
    public void add(List<IPowerUp> listOfPowerUps){
        for (IPowerUp pu : listOfPowerUps){
            add(pu);
        }
    }
    @Override
    public void incHealth(){
        health++;
    }
    @Override
    public void decHealth() { health--; }
    @Override
    public SpaceCraftType getType(){
        return type;
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
    public void handleCollision(Collidable collidable) {
        if (collidable.getCollidableType() == CollidableType.OBSTACLE || collidable.getCollidableType() == CollidableType.HARMFUL){
            //Take damage if collided with obstacle or harmful
            AudioQueue.getInstance().playSound("flashBang.mp3","sounds/effects", 0.2f);
            decHealth();
        } else if (collidable.getCollidableType() == CollidableType.CONTAINER){
            //take powerup of collided with container
            System.out.println("POWERUP COLLECTED");
            IContainer container = (IContainer) collidable;
            add(container.getPowerUp());
        }
    }
}
