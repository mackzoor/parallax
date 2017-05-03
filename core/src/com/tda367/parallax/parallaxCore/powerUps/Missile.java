package com.tda367.parallax.parallaxCore.powerUps;

import com.tda367.parallax.CoreAbstraction.Model;
import com.tda367.parallax.CoreAbstraction.RenderManager;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by xoxLU on 2017-05-02.
 */
public class Missile implements IPowerUp {

    private static final String modelName = "missile.g3db";
    private Vector3f pos;
    private Quat4f rot;
    private Boolean collisionEnabled;
    private Model missileModel;

    public Missile(){
    }

    //Creating a missile with position set in the constructor
    public Missile(Vector3f pos, Quat4f rot, Boolean collision){
        this.pos = pos;
        this.rot = rot;
        collisionEnabled = collision;
    }

    //Creation of missile with standard settings (collision enabled)
    public Missile(Vector3f pos, Quat4f rot){
        this(pos, rot, true);
    }

    public static String getModelName() {
        return modelName;
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
        return null;
    }

    @Override
    public void addToRenderManager() {
        RenderManager.getInstance().addRenderTask(this);
    }

    @Override
    public void removeFromRenderManager() {
        RenderManager.getInstance().removeRenderTask(this);
    }

    @Override
    public Vector3f getPos() {
        return pos;
    }

    @Override
    public Quat4f getRot() {
        return rot;
    }

    @Override
    public void usePU(Vector3f pos, Quat4f rot){
    }

    @Override
    public void activate() {
    }

    @Override
    public void update(int milliSinceLastUpdate) {
    }

    @Override
    public Model getModel() {
        return missileModel;
    }
}
