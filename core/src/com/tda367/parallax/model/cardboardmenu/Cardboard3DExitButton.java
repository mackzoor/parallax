package com.tda367.parallax.model.cardboardmenu;

import com.tda367.parallax.model.util.Model;
import com.tda367.parallax.model.coreabstraction.RenderQueue;
import com.tda367.parallax.model.util.Renderable;
import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by Rasmus on 2017-05-08.
 */
public class Cardboard3DExitButton implements Renderable, Collidable {

    private Vector3f pos;
    private Quat4f rot;
    private boolean isHit;

    private Model model;

    private Model collisionModel;

    private boolean collisionEnabled;

    public Cardboard3DExitButton(){
        pos = new Vector3f();
        rot = new Quat4f();
        model = new Model("boxObstacle.g3db", "3dModels/boxObstacle");
        collisionModel = new Model(model.getModelName(), model.getModelDirectory());
        collisionEnabled = true;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void addToRenderManager() {
        RenderQueue.getInstance().addRenderTask(this);
    }

    @Override
    public void removeFromRenderManager() {
        RenderQueue.getInstance().removeRenderTask(this);
    }

    @Override
    public Vector3f getPos() {
        return pos;
    }

    @Override
    public Quat4f getRot() {
        return rot;
    }

    public boolean isActive() {
        return collisionEnabled;
    }

    @Override
    public boolean collisionActivated() {
        return false;
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
        return CollidableType.CONTAINER;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if(collidable.getCollidableType() == CollidableType.HARMFUL){
            isHit = true;
        }
    }

    public void setPos(Vector3f vector3f){
        pos = vector3f;
    }

    public boolean isCollided(){
        return isHit;
    }
}
