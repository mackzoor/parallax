package com.tda367.parallax.view.menu;

import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


public class BackgroundView implements View {


    private final String internalPath = "3dModels/defaultCourse/course.g3db";
    private final Renderable3dObject renderable3dObject;

    BackgroundView() {
        this.renderable3dObject = new Renderable3dObject(
                new Vector3f(),
                new Quat4f(),
                ResourceLoader.getInstance().getModel(this.internalPath)
                , 1f);
    }

    @Override
    public void render() {
        Renderer3D.getInstance().addObjectToFrame(this.renderable3dObject);
    }

    @Override
    public boolean isObsolete() {
        return false;
    }

}
