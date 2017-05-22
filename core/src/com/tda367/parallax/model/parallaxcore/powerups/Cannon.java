package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.model.parallaxcore.util.Transformable;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.Random;

/**
 * The cannon PowerUp fires a cannon shot towards the direction it is pointed at.
 */

public class Cannon implements IPowerUp {

    private Vector3f pos;
    private Quat4f rot;
    private Vector3f velocity;

    private String collisionModel;
    private boolean isCollisionOn;

    private boolean isActive;
    private boolean isDead;

    private int timeAlive;
    private final static int LIFE_LENGTH = 4000;

    public Cannon(){
        timeAlive = 0;
        this.pos = new Vector3f();
        this.rot = new Quat4f();
        this.velocity = new Vector3f();
        this.isCollisionOn = false;

        isActive = false;
        isDead = false;
    }

    private Vector3f quatToDirection(Quat4f q){
        float div = (float) Math.sqrt(q.x * q.x + q.y * q.y + q.z * q.z);
        float x = q.x / div;
        float y = q.y / div;
        float z = q.z / div;

        Vector3f vec = new Vector3f(-z, x,-y);
        vec.normalize();
        return vec;
    }

    //Launches the cannon round.
    @Override
    public void activate(Transformable transformable) {

        //TODO Make lazer shoot in the direction that the transformable is rotated.
        //Offset cannon round rotation by 90 degrees due to rotated 3d model.
//        this.rot = new Quat4f(0,0,0.7071f,0.7071f);

        //Rotate the cannon round with the given rotation.
//        this.rot.mul(transformable.getRot());

        //Sets the cannon round starting position to the one given in the arguments.
//        this.pos = new Vector3f(transformable.getPos());

//        velocity = quatToDirection(transformable.getRot());


        //Placeholder
        pos = new Vector3f(transformable.getPos());
        pos.add(new Vector3f(0,4,0));
        velocity = new Vector3f(0,1,0);



        velocity.scale(30);

        isActive = true;
        isCollisionOn = true;

        playCannonSound();
        addToCollisionManager();
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void use() {
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    //Updates the cannon.
    @Override
    public void update(int milliSinceLastUpdate){
        if (isActive){
            timeAlive += milliSinceLastUpdate;
            updatePosition(milliSinceLastUpdate);
        }
        if (timeAlive > LIFE_LENGTH){
            die();
        }
    }
    private void die() {
        removeFromCollisionManager();
        disableCollision();
        isActive = false;
        isDead = true;
    }
    private void updatePosition(int milliSinceLastUpdate){
        pos.add(new Vector3f(velocity.getX() * ((float) milliSinceLastUpdate/1000),(velocity.getY() * ((float) milliSinceLastUpdate/1000)),(velocity.getZ() * ((float)milliSinceLastUpdate/1000))));
    }
    private void playCannonSound(){
        Random rand = new Random();
        int randomSong = rand.nextInt(200 - 1 + 1) + 1;

        //Plays a funny sound every 200 shots
        if(randomSong > 199){
            AudioQueue.getInstance().playSound("cannonLow.mp3","sounds/effects", 0.3f);
        } else {
            AudioQueue.getInstance().playSound("cannon.mp3","sounds/effects", 0.8f);
        }
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
    public String getCollisionModelPath() {
        return collisionModel;
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
    public void handleCollision(Collidable collidable) {
        if ( timeAlive > 250 && collidable.getCollidableType() == CollidableType.SPACECRAFT) {
            die();
        }
    }
}

