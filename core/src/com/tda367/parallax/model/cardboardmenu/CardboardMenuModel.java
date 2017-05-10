package com.tda367.parallax.model.cardboardmenu;

import com.tda367.parallax.model.coreabstraction.Model;
import com.tda367.parallax.model.coreabstraction.RenderQueue;
import com.tda367.parallax.model.coreabstraction.Renderable;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by Rasmus on 2017-05-08.
 */
public class CardboardMenuModel implements Renderable {

    private Vector3f pos;
    private Quat4f rot;

    private Model model;

    public CardboardMenuModel(){
        model = new Model("course.g3db", "3dModels/defaultCourse");
        pos = new Vector3f();
        rot = new Quat4f();

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
}
