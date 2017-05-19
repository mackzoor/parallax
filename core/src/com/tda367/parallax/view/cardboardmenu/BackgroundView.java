package com.tda367.parallax.view.cardboardmenu;

import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.parallaxview.View;
import com.tda367.parallax.view.util.Renderable3dObject;
import com.tda367.parallax.view.util.ResourceLoader;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


public class BackgroundView implements View {


    private String internalPath;
    private Renderable3dObject renderable3dObject;

    BackgroundView(){
        internalPath = "/3dModels/defaultCourse/course.g3db";
        renderable3dObject = new Renderable3dObject(
                new Vector3f(),
                new Quat4f(),
                ResourceLoader.getInstance().getModel("3dModels/defaultCourse/course.g3db")
                ,1f);
    }

    @Override
    public void render(){
        Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
    }

    @Override
    public boolean isObsolete() {
        return false;
    }

}
