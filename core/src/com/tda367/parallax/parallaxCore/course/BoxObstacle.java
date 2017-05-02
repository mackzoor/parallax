package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.CoreAbstraction.*;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * A cube that is renderable and collidable.
 */
public class BoxObstacle implements com.tda367.parallax.CoreAbstraction.Collidable, Renderable {
    private Vector3f pos;
    private Quat4f rot;

    private com.tda367.parallax.CoreAbstraction.Model model;
    private com.tda367.parallax.CoreAbstraction.Model collisionModel;

    private boolean collisionEnabled;


    public BoxObstacle(){
        model = new com.tda367.parallax.CoreAbstraction.Model("boxObstacle.g3db", "3dModels/boxObstacle");
        collisionModel = new com.tda367.parallax.CoreAbstraction.Model(model.getModelName(), model.getModelDirectory());
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
        com.tda367.parallax.CoreAbstraction.RenderManager.getInstance().addRenderTask(this);
    }
    @Override
    public void removeFromRenderManager() {
        com.tda367.parallax.CoreAbstraction.RenderManager.getInstance().removeRenderTask(this);
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
    public boolean isActive() {
        return collisionEnabled;
    }
    @Override
    public Model getCollisionModel() {
        return collisionModel;
    }

    @Override
    public Model getModel() {
        return model;
    }
}
