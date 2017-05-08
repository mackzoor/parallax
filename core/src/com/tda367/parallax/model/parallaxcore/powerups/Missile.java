package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.coreabstraction.Model;
import com.tda367.parallax.model.coreabstraction.RenderManager;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Getter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class Missile implements IPowerUp {

    //Missile variables
    private Vector3f pos;
    private Quat4f rot;
    private Vector3f velocity;
    private Vector3f acceleration;

    private Model model;
    private Model collisionModel;
    private boolean isCollisionOn;
    private boolean isActive;
    private boolean isDead;

    //Target variables
    @Getter private Vector3f enemyTargetPosition;

    public Missile(){
        this.pos = new Vector3f();
        this.rot = new Quat4f();
        this.velocity = new Vector3f();
        this.acceleration = new Vector3f();
        this.model = new Model("missile.g3db", "3dModels/missile");
        this.isCollisionOn = false;

        isActive = true;
        isDead = false;


        addToRenderManager();
        addToCollisionManager();
    }

    //IPowerUp
    @Override
    public void activate(Vector3f pos, Quat4f rot){
        isActive = true;
    }

    @Override
    public boolean isActive() {
        return isActive;
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
    public void addToCollisionManager() {
        CollisionManager.getInstance().addCollisionCheck(this);
    }
    @Override
    public void removeFromCollisionManager() {
        CollisionManager.getInstance().removeCollisionCheck(this);
    }
    @Override
    public CollidableType getCollidableType() {
        return CollidableType.HARMFUL;
    }
    @Override
    public void handleCollision(CollidableType type) {
        //Todo Create explosion

        if (type != CollidableType.SPACECRAFT) {

            isActive = false;
            isDead = true;
            removeFromRenderManager();
            removeFromCollisionManager();
        }
    }


    //Renderable
    @Override
    public void addToRenderManager() {
        RenderManager.getInstance().addRenderTask(this);
    }
    @Override
    public void removeFromRenderManager() {
        RenderManager.getInstance().removeRenderTask(this);
    }
    //--->  IModel
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

    @Override
    public boolean isDead() {
        return isDead;
    }


    //Updatable
    @Override
    public void update(int milliSinceLastUpdate) {
        rotateShip(getEnemyTargetPosition());
        Vector3f directionalVector = generateDirectionVector(getEnemyTargetPosition());
        moveOnDirectionVector(directionalVector, milliSinceLastUpdate);
    }



    //Ship movement and rotation

    //Method for ship rotation, rotating to a input target.
    private void rotateShip(Vector3f target){
        getPos();

        //getRot().set(/*insertNewRotationHere*/);
    }

    //Method to generate a direction vector for the ship's movement.
    private Vector3f generateDirectionVector(Vector3f target){
        getPos();

        //TODO, generate a direction vector based on the target position subtracted with the position of the ship.

        //TODO, Normalize the directional vector before sending it away.

        //Return a new vector with the current direction vector
        return new Vector3f();
    }

    //Method for moving the ship based on the direction vector generated from method "generateDirectionalVector"
    private void moveOnDirectionVector(Vector3f directionalVector, int milliSinceLastUpdate){
        getPos();

        //TODO, generate new position based on earlier position added together with the (directional vector multiplied with time/1000)

        //getPos().set(/*Insert new position here*/);
    }
}
