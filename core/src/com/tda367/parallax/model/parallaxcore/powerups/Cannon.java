package com.tda367.parallax.model.parallaxcore.powerups;


import com.tda367.parallax.model.coreabstraction.Model;
import com.tda367.parallax.model.coreabstraction.RenderManager;
import com.tda367.parallax.model.coreabstraction.SoundManager;

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
    private Vector3f acceleration; //Will it ever use this?

    private Model model;
    private Model collisionModel;
    private boolean isCollisionOn;

    public Cannon(){
        this.pos = new Vector3f();
        this.rot = new Quat4f();
        this.velocity = new Vector3f();
        this.acceleration = new Vector3f();
        this.model = new Model("laser.g3db", "3dModels/laser");
        this.isCollisionOn = false;
    }

    //Launches the cannon round.
    @Override
    public void usePU(Vector3f pos, Quat4f rot) {
        //Offset cannon round rotation by 90 degrees due to rotated 3d model.
        this.rot = new Quat4f(0,0,0.7071f,0.7071f);

        //Rotate the cannon round with the given rotation.
//        this.rot.mul(rot);

        //Sets the cannon round starting position to the one given in the arguments.
        this.pos = new Vector3f(pos);

        velocity.setY(20);

        //Plays a sound for the laser
        playCannonSound();
    }
    @Override
    public void activate() {
    }

    //Updates the cannon.
    @Override
    public void update(int milliSinceLastUpdate){
        updatePosition(milliSinceLastUpdate);
    }
    private void updatePosition(int milliSinceLastUpdate){
        pos.add(new Vector3f(velocity.getX() * ((float) milliSinceLastUpdate/1000),(velocity.getY() * ((float) milliSinceLastUpdate/1000)),(velocity.getZ() * ((float)milliSinceLastUpdate/1000))));
    }

    private void playCannonSound(){
        Random rand = new Random();
        int randomSong = rand.nextInt(200 - 1 + 1) + 1;

        //Plays a funny sound every 200 shots
        if(randomSong > 199){
            SoundManager.getInstance().playSound("cannonLow.mp3","sounds/effects", new Float(0.8f));
        } else {
            SoundManager.getInstance().playSound("cannon.mp3","sounds/effects", new Float(0.8f));
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
    public boolean isActive() {
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


    //Render
    @Override
    public void addToRenderManager() {
        RenderManager.getInstance().addRenderTask(this);
    }
    @Override
    public Model getModel() {
        return model;
    }
    @Override
    public void removeFromRenderManager() {
        RenderManager.getInstance().addRenderTask(this);
    }

}

