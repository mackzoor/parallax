package com.tda367.parallax.model.parallaxCore.powerUps;

import com.tda367.parallax.model.CoreAbstraction.Model;
import com.tda367.parallax.model.CoreAbstraction.RenderManager;
import com.tda367.parallax.model.CoreAbstraction.SoundManager;
import com.tda367.parallax.model.CoreAbstraction.Transformable;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by xoxLU on 2017-05-02.
 */
public class Missile implements IPowerUp {

    private Vector3f pos;
    private Quat4f rot;
    private Vector3f velocity;
    private Vector3f acceleration;

    private Model model;
    private Model collisionModel;
    private boolean isCollisionOn;

    public Missile(){
        this.pos = new Vector3f();
        this.rot = new Quat4f();
        this.velocity = new Vector3f();
        this.acceleration = new Vector3f();
        this.model = new Model("missile.g3db", "3dModels/missile");
        this.isCollisionOn = false;
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
        return model;
    }
}
