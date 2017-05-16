package com.tda367.parallax.view.util;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Class for 3d renderable objects.
 */
public class Renderable3dObject implements Renderable {

    private Vector3f pos;
    private Quat4f rot;
    private Model model;
    private float opacity;

    public Renderable3dObject(Vector3f pos, Quat4f rot, Model model, float opacity) {
        this.pos = pos;
        this.rot = rot;
        this.model = model;
        this.opacity = opacity;
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
    public float getOpacity() {
        return opacity;
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
