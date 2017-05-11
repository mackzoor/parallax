package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.util.Model;
import com.tda367.parallax.model.coreabstraction.RenderQueue;
import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.model.util.Transformable;
import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class Missile implements IPowerUp {

    //Missile variables
    private Vector3f pos;
    private Quat4f rot;
    @Setter float velocity;
    private Vector3f acceleration;

    private Model model;
    private Model collisionModel;
    private boolean isCollisionOn;
    private boolean isActive;
    private boolean isDead;

    private Vector3f transformableEarlierPosition;
    private Transformable transformable;

    //Total time the missile has been activated
    private int timeStorage;

    //Time to represent the falling phase, value compared to the timeStorage variable
    private static final int fallTime = 500;

    //Time to represent the active phase, value compared to the timeStorage variable
    private static final int activeTime = 5000;

    //Multiplier to change the speed of missile
    private static final float velocityMultiplier = 4f;
    private static final float fallMultiplier = 2.5f;

    //Target variables
    @Getter private Vector3f enemyTargetPosition;



    public Missile(){
        this.pos = new Vector3f();
        this.rot = new Quat4f();
        this.acceleration = new Vector3f();
        this.model = new Model("missile.g3db", "3dModels/missile");
        this.isCollisionOn = false;
        this.enemyTargetPosition = new Vector3f();

        isActive = false;
        isDead = false;
    }

    //IPowerUp
    @Override
    public void activate(Transformable transformable){
        //Initialize the ships position and rotation based on the position of the transformable object.
        pos = new Vector3f(transformable.getPos());
        rot = new Quat4f(transformable.getRot());

        //Store the transformable object for later use
        this.transformable = transformable;

        //Add the missile to the world and make it collidable
        addToRenderManager();
        addToCollisionManager();

        //Allow the update method to move the missile
        isActive = true;


        //TODO, Temporary initialization for the enemy, will be controlled elsewhere later.
        enemyTargetPosition.set(new Vector3f(pos));
        enemyTargetPosition.add(new Vector3f(0,100,20));
    }
    @Override
    public boolean isActive() {
        return isActive;
    }
    @Override
    public boolean isDead() {
        return isDead;
    }

    //Collision
    @Override
    public boolean collisionActivated() {
        return isCollisionOn;
    }
    @Override
    public void disableCollision() {
        isCollisionOn = false;
    }
    @Override
    public void enableCollision() {
        isCollisionOn = true;
    }
    @Override
    public Model getCollisionModel() {
        return null;
    }
    @Override
    public final void addToCollisionManager() {
        CollisionManager.getInstance().addCollisionCheck(this);
    }
    @Override
    public final void removeFromCollisionManager() {
        CollisionManager.getInstance().removeCollisionCheck(this);
    }
    @Override
    public CollidableType getCollidableType() {
        return CollidableType.HARMFUL;
    }
    @Override
    public void handleCollision(Collidable collidable) {
        //Todo Create explosion

        isActive = false;
        isDead = true;
        removeFromRenderManager();
        removeFromCollisionManager();
    }


    //Renderable
    @Override
    public final void addToRenderManager() {
        RenderQueue.getInstance().addRenderTask(this);
    }
    @Override
    public final void removeFromRenderManager() {
        RenderQueue.getInstance().removeRenderTask(this);
    }
    @Override
    public Model getModel() {
        return model;
    }


    //--->  Transformable
    @Override
    public Vector3f getPos() {
        return pos;
    }
    @Override
    public Quat4f getRot() {
        return rot;
    }


    //Usable
    @Override
    public void use() {
    }


    //Updatable
    @Override
    public void update(int milliSinceLastUpdate){
        //Check that the missile is not dead before updating
        if(!isDead) {
           //Check if the missile is going to move
           if (isActive) {
               //Adds to the total time the missile has been activated.
               timeStorage = timeStorage + milliSinceLastUpdate;

               //Checks to see that the missile has gotten a velocity before moving
               //Before testing velocity, check that velocity generation is allowed, by using previous and current position.
               if (transformableEarlierPosition == null) {
                   transformableEarlierPosition = new Vector3f(transformable.getPos());
                   //Make sure that the missile has the same position as the ship after one cycle.
                   pos.set(transformable.getPos());
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

        //Two states of movement, decided based on the time spent active. Either falling or moving towards the target.

        //Check if the missile is in the falling phase, moving to target phase or if the missile should be removed.
        if(timeStorage <= fallTime){

            //Move based on the velocity (Transformable object velocity)
            moveOnVelocity(milliSinceLastUpdate);

            //Additional falling, simulating the missile aiming on the target
            fall(milliSinceLastUpdate);

        }else if(timeStorage <= activeTime){

            //Move based on the velocity (Transformable object velocity)
            moveOnVelocity(milliSinceLastUpdate);

            //Additional moving and rotating to get the missile to the target location
            rotateShip(getEnemyTargetPosition());
            Vector3f directionalVector = generateDirectionVector(getEnemyTargetPosition());
            moveOnDirectionVector(directionalVector, milliSinceLastUpdate);

        } else{
            removeMissile();
        }
    }

    //Method for calling methods removing the missile
    public void removeMissile(){
        removeFromCollisionManager();
        removeFromRenderManager();
        isDead = true;
    }

    //Method for making the missile fall
    private void fall(int milliSinceLastUpdate){
        pos.add(new Vector3f(0,0,-(((float)milliSinceLastUpdate)/1000)*fallMultiplier));
    }

    //Method for moving the missile according to the velocity
    public void moveOnVelocity(int milliSinceLastUpdate){
        float posYAdded = velocity * ((float)milliSinceLastUpdate/1000);
        pos.add(new Vector3f(0, posYAdded, 0));
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
        getPos().add(new Vector3f((directionalVector.getX()*(float)milliSinceLastUpdate/1000)*velocityMultiplier, (directionalVector.getY()*(float)milliSinceLastUpdate/1000)*velocityMultiplier, (directionalVector.getZ()*(float)milliSinceLastUpdate/1000)*velocityMultiplier));
    }
}
