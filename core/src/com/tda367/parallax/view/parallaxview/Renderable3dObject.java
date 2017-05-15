package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.view.Model;
import com.tda367.parallax.view.Renderable;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Class for 3d renderable objects.
 */
public class Renderable3dObject implements Renderable {

    private Vector3f pos;
    private Quat4f rot;
    private Model model;

    public Renderable3dObject(Vector3f pos, Quat4f rot, Model model) {
        this.pos = pos;
        this.rot = rot;
        this.model = model;
    }

    @Override
    public void addToRenderManager() {

    }
    @Override
    public void removeFromRenderManager() {

    }


    @Override
    public Model getModel() {
        return model;
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
