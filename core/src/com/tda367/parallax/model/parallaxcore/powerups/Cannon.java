package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.util.Model;
import com.tda367.parallax.model.coreabstraction.RenderQueue;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.model.util.Transformable;

import javax.vecmath.Matrix4f;
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

    private Model model;
    private Model collisionModel;
    private boolean isCollisionOn;

    private boolean isActive;
    private boolean isDead;

    private int timeAlive;
    private final int lifeLength = 4000;

    public Cannon(){
        timeAlive = 0;
        this.pos = new Vector3f();
        this.rot = new Quat4f();
        this.velocity = new Vector3f();
        this.model = new Model("laser.g3db", "3dModels/laser");
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
    public void activate(Transformable ship) {
        //Offset cannon round rotation by 90 degrees due to rotated 3d model.
        this.rot = new Quat4f(0,0,0.7071f,0.7071f);

        //Rotate the cannon round with the given rotation.
        this.rot.mul(ship.getRot());

        //Sets the cannon round starting position to the one given in the arguments.
        this.pos = new Vector3f(ship.getPos());

        velocity = quatToDirection(ship.getRot());
        velocity.scale(20);

        isActive = true;
        isCollisionOn = true;

        playCannonSound();
        addToCollisionManager();
        addToRenderManager();
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
            if (timeAlive > lifeLength){
                removeFromRenderManager();
                removeFromCollisionManager();
                isDead = true;
            }
        }
    }
    private void updatePosition(int milliSinceLastUpdate){
        pos.add(new Vector3f(velocity.getX() * ((float) milliSinceLastUpdate/1000),(velocity.getY() * ((float) milliSinceLastUpdate/1000)),(velocity.getZ() * ((float)milliSinceLastUpdate/1000))));
    }



    private void playCannonSound(){
        Random rand = new Random();
        int randomSong = rand.nextInt(200 - 1 + 1) + 1;

        //Plays a funny sound every 200 shots
        if(randomSong > 199){
            AudioQueue.getInstance().playSound("cannonLow.mp3","sounds/effects", 0.8f);
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
    public Model getCollisionModel() {
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
        //TODO Disable powerup
    }


    //Render
    @Override
    public void addToRenderManager() {
        RenderQueue.getInstance().addRenderTask(this);
    }
    @Override
    public Model getModel() {
        return model;
    }
    @Override
    public void removeFromRenderManager() {
        RenderQueue.getInstance().addRenderTask(this);
    }
}

