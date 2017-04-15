package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.parallaxCore.*;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by amk19 on 11/04/2017.
 */
public class Obstacle implements Collidable, Renderable, Updatable {
    private Vector3f pos;
    private Quat4f rot;

    private Model model;

    public Obstacle(){
        pos = new Vector3f();
        rot = new Quat4f();
        model = new Model("boxObstacle.g3db", "3dModels/boxObstacle");
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
}
