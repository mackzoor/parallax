package com.tda367.parallax.model.cardboardmenu;

import com.tda367.parallax.view.util.Model;
import com.tda367.parallax.view.util.Renderable;
import com.tda367.parallax.view.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class Cardboard3DMenuModel implements Renderable {

    private Vector3f pos;
    private Quat4f rot;

    private Model model;

    public Cardboard3DMenuModel(){
        model = new Model("course.g3db", "3dModels/defaultCourse");
        pos = new Vector3f();
        rot = new Quat4f();

    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public float getOpacity() {
        return 1f;
    }

    @Override
    public void addToRenderManager() {
        Renderer3D.getInstance().addObjectToFrame(this);
    }

    @Override
    public void removeFromRenderManager() {
        Renderer3D.getInstance().addObjectToFrame(this);
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
