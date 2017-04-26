package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.parallaxCore.*;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by amk19 on 11/04/2017.
 */
public class BoxObstacle implements Collidable, Renderable, Updatable {
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
    public Model getModel() {
        return model;
    }

    @Override
    public void update(int milliSinceLastUpdate) {

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

    public void enableCollision(){
        collisionEnabled = true;
    }

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
}
