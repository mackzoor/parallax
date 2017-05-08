package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.coreabstraction.Model;
import com.tda367.parallax.model.coreabstraction.RenderManager;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

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
    public void addToCollisionManager() {
        CollisionManager.getInstance().addCollisionCheck(this);
    }
    @Override
    public void removeFromCollisionManager() {
        CollisionManager.getInstance().removeCollisionCheck(this);
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
