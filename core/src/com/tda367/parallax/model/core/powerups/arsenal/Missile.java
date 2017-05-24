package com.tda367.parallax.model.core.powerups.arsenal;


import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;

import com.tda367.parallax.model.core.util.Transformable;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.utilities.MathUtilities;

import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Vector3f;

public class Missile extends PowerUpBase {
    //Ships velocity at the start of the ACCELERATION phase
    private float startVelocity;

    //Velocity that the missile is moving in.
    @Setter private float velocity;

    //Time that the missile has accelerated
    private int accelerationTime;

    //Path to 3D model for  missile collision
    private String collisionModel;

    //Saving variables for the transformable object the missile has its origin from
    private Vector3f transformableEarlierPosition;
    private Transformable transformable;
    //Storing the position of the transformable object since last update, making it possible to track its movement
    private Vector3f transPosLastUpdate;

    //Total time the missile has been activated
    private int timeStorage;

    //Target variables
    @Getter private Vector3f enemyTargetPosition;



    /*
        Variables for toggling the missile movement
    */

    //Time to represent the falling phase, value compared to the timeStorage variable
    private static final int FALL_TIME = 500;

    //Time to represent the active phase, value compared to the timeStorage variable
    private static final int ACTIVE_TIME = 5000;

    //Time that missile is tracking the ships movement, making sure that it doesn't go trough it.
    private static final int TIME_TRACKING_TRANS = 600;

    //Multiplier to change the speed of missile
    private static final float VELOCITY_MULTIPLIER_X = 10f;
    private static final float VELOCITY_MULTIPLIER_Y = 1f;
    private static final float VELOCITY_MULTIPLIER_Z = 10f;
    private static final float FALL_MULTIPLIER = 2.5f;

    //Internal path to 3d collision model
    private static final String COLLISION_MODEL = "";

    //How fast the missile will accelerate
    private static final double ACCELERATION = 0.8;

    //Maximum velocity that the missile can reach.
    private static final float MAXIMUM_VELOCITY = 80;


    //Constructor for missile, deceleration for most variables.
    Missile(){
        super();
        this.collisionModel ="3dModels/box/hitbox.obj";
        this.enemyTargetPosition = new Vector3f();
        this.transPosLastUpdate = new Vector3f();
    }

    //IPowerUp
    @Override
    public void activate(Transformable transformable){
        //Initialize the ships position and rotation based on the position of the transformable object.
        super.activate(transformable);
        
        //Add the missile to the world and make it collidable
        addToCollisionManager();

        //Store the transformable object for later use
        this.transformable = transformable;

        //Allow the update method to move the missile
        super.setActive(true);

        //TODO, Temporary initialization for the enemy, will be controlled elsewhere later.
        enemyTargetPosition.set(new Vector3f(super.getPos()));
        enemyTargetPosition.add(new Vector3f(0,100,20));

        //Plays the missile sound.
        playMissileSound();
    }
    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL;
    }
    @Override
    public CollidableType getCollidableType() {
        return CollidableType.HARMFUL;
    }
    @Override
    public void handleCollision(Collidable collidable) {
        //Todo Create explosion
        if (collidable.getCollidableType() == CollidableType.SPACECRAFT && timeStorage > 250){
            super.setActive(false);
            super.setDead(true);
            removeFromCollisionManager();
        }
    }
    

    //Usable
    @Override
    public void use() {
    }


    //TODO, remove the fact that missile moves faster towards things further away.

    //Updatable, things that control the missile each update
    @Override
    public void update(int milliSinceLastUpdate){
        //Check that the missile is not dead before updating
        if(!super.isDead()) {
           //Check if the missile is going to move
           if (super.isActive()) {
               //Adds to the total time the missile has been activated.
               timeStorage = timeStorage + milliSinceLastUpdate;

               //Checks to see that the missile has gotten a velocity before moving
               //Before testing velocity, check that velocity generation is allowed, by using previous and current position.
               if (transformableEarlierPosition == null) {
                   transformableEarlierPosition = new Vector3f(transformable.getPos());
                   //Make sure that the missile has the same position as the ship after one cycle.
                   super.getPos().set(transformable.getPos());
               } else if (velocity == 0) {
                   //Generate velocity for the missile, based on the velocity of the transformable object
                   generateVelocity(transformableEarlierPosition, transformable, milliSinceLastUpdate);
               } else {
                   moveTheMissile(milliSinceLastUpdate, timeStorage);
               }
           }
        }
    }



    //Methods for movement

    //Method for generating the velocity for the missile, gets the missile velocity based on the velocity of the transformable object.
    private void generateVelocity(Vector3f transformableEarlierPostion, Transformable transformable, int milliSinceLastUpdate){
        setVelocity((transformable.getPos().getY() - transformableEarlierPostion.getY())/((float)milliSinceLastUpdate)*1000);
    }

    //Method for general missile movement, separated from update for nicer looking code.
    private void moveTheMissile(int milliSinceLastUpdate, int timeStorage){

        //System.out.println(pos.getX() + ", " + pos.getY() + ", " + pos.getZ());

        if(timeStorage <= TIME_TRACKING_TRANS){
            if(transPosLastUpdate.getX() == 0 && transPosLastUpdate.getY() == 0 && transPosLastUpdate.getZ() == 0){
                super.setPos(new Vector3f(transformable.getPos()));
                transPosLastUpdate = new Vector3f(transformable.getPos());
            }else{
                if(transPosLastUpdate.getZ() > transformable.getPos().getZ()){
                    super.getPos().add(new Vector3f(0,0, transformable.getPos().getZ() - transPosLastUpdate.getZ()));
                }
            }
            transPosLastUpdate = new Vector3f(transformable.getPos());
        }

        //Two states of movement, decided based on the time spent active. Either falling or moving towards the target.

        //Check if the missile is in the falling phase, moving to target phase or if the missile should be removed.
        if(timeStorage <= FALL_TIME){

            //Move based on the velocity (Transformable object velocity)
            moveOnVelocity(milliSinceLastUpdate);

            //Additional falling, simulating the missile aiming on the target
            fall(milliSinceLastUpdate);


        }else if(timeStorage <= ACTIVE_TIME){

            //Add more velocity to the ship
            accelerateMissile(milliSinceLastUpdate);

            //Move based on the velocity (Transformable object velocity)
            moveOnVelocity(milliSinceLastUpdate);

            //Additional moving and rotating to get the missile to the target location
            rotateShip(getEnemyTargetPosition());
            if(timeStorage > TIME_TRACKING_TRANS){
                Vector3f directionalVector = generateDirectionVector(getEnemyTargetPosition());
                moveOnDirectionVector(directionalVector, milliSinceLastUpdate);
            }
        } else{
            removeMissile();
        }
    }

    public void accelerateMissile(int milliSinceLastUpdate){

        //Adds time since last update to the accelerationTime variable
        accelerationTime = accelerationTime + milliSinceLastUpdate;

        //Makes the starting velocity the current velocity when the missile is said to accelerate
        if(startVelocity == 0){
            startVelocity = velocity;
        }

        //Check to see that the MAXIMUM_VELOCITY has not been reached, if it has, make the velocity the MAXIMUM_VELOCITY
        if (velocity < MAXIMUM_VELOCITY) {
            /*Calculate the velocity based on this formula: x(t) = x0 × (1 + r)^t
            x(t) is the value at time t.
            x0 is the initial value at time t=0.
            r is the growth rate when r>0 or decay rate when r<0, in percent.
            t is the time in discrete intervals and selected time units.
             */
            velocity = startVelocity * ((float) Math.pow((1 + ACCELERATION), (((double) accelerationTime) / 1000)));
        }else{
            //Makes the MAXIMUM_VELOCITY the current velocity, if reached overflow velocity
            velocity = MAXIMUM_VELOCITY;
        }
    }

    //Method for calling methods removing the missile
    public void removeMissile(){
        removeFromCollisionManager();
        super.setDead(true);
        super.setActive(false);
    }

    //Method for making the missile fall
    private void fall(int milliSinceLastUpdate){
        super.getPos().add(new Vector3f(0,0,-(((float)milliSinceLastUpdate)/1000)* FALL_MULTIPLIER));
    }

    //Method for moving the missile according to the velocity
    public void moveOnVelocity(int milliSinceLastUpdate){
        float posYAdded = velocity * ((float)milliSinceLastUpdate/1000);
        super.getPos().add(new Vector3f(0, posYAdded, 0));
    }

    //Method for ship rotation, rotating to an input target.
    private void rotateShip(Vector3f target){
        //getPos();

        //getRot().set(/*insertNewRotationHere*/);
    }

    //Method to generate a direction vector (normalized) for the ship's movement.
    private Vector3f generateDirectionVector(Vector3f target){
        //Generate a direction vector based on the target position subtracted with the position of the ship.
        Vector3f returnVector = new Vector3f(target.getX()-getPos().getX(), target.getY()-getPos().getY(), target.getZ()-getPos().getZ());

        //Normalize the directional vector before sending it away.
        returnVector.normalize();

        //Return a new vector with the current direction vector
        return returnVector;
    }

    //Method for moving the ship based on the direction vector generated from method "generateDirectionalVector"
    private void moveOnDirectionVector(Vector3f directionalVector, int milliSinceLastUpdate){
        getPos().add(new Vector3f((directionalVector.getX()*(float)milliSinceLastUpdate/1000)* VELOCITY_MULTIPLIER_X, (directionalVector.getY()*(float)milliSinceLastUpdate/1000)* VELOCITY_MULTIPLIER_Y, (directionalVector.getZ()*(float)milliSinceLastUpdate/1000)* VELOCITY_MULTIPLIER_Z));
        this.setRot(MathUtilities.vectorToQuat(directionalVector));
    }


    //Sound

    //Method for playing missile shooting sound
    private void playMissileSound(){
        AudioQueue.getInstance().playSound("MissileDemo.mp3","sounds/effects", 0.7f);
    }

}
