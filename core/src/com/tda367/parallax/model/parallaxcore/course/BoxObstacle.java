package com.tda367.parallax.model.parallaxcore.course;

import com.tda367.parallax.model.coreabstraction.SoundManager;
import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.coreabstraction.Model;
import com.tda367.parallax.model.coreabstraction.RenderManager;
import com.tda367.parallax.model.coreabstraction.Renderable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * A cube that is renderable and collidable.
 */

public class BoxObstacle implements Collidable, Renderable {
    private Vector3f pos;
    private Quat4f rot;

    private Model model;
    private Model collisionModel;

    private boolean collisionEnabled;

    public BoxObstacle(){
        model = new Model("boxObstacle.g3db", "3dModels/boxObstacle");
        collisionModel = new Model(model.getModelName(), model.getModelDirectory());
        pos = new Vector3f();
        rot = new Quat4f();
        collisionEnabled = true;
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
    public void addToRenderManager() {
        RenderManager.getInstance().addRenderTask(this);
    }
    @Override
    public void removeFromRenderManager() {
        RenderManager.getInstance().removeRenderTask(this);
    }
    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void enableCollision(){
        collisionEnabled = true;
    }
    @Override
    public void disableCollision(){
        collisionEnabled = false;
    }
    @Override
    public boolean collisionActivated() {
        return collisionEnabled;
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
        return CollidableType.OBSTACLE;
    }

    @Override
    public void handleCollision(CollidableType type) {
        if (type == CollidableType.SPACECRAFT){
            disableCollision();
            SoundManager.getInstance().playSound("flashBang.mp3","sounds/effects", 0.1f);
        }
    }
}
