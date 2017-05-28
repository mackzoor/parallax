package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.worldview;

import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * A background scenery 3d model to ease into the main course 3d model.
 */
public class CourseIntroView implements View {

    private final String internalPath = "3dModels/courseintro/courseintro.g3db";
    private final Renderable3dObject renderable3dObject;

    public CourseIntroView() {
        this.renderable3dObject = new Renderable3dObject(
                new Vector3f(),
                new Quat4f(),
                ResourceLoader.getInstance().getModel(this.internalPath),
                1f);
    }

    @Override
    public void render() {
        Renderer3D.getInstance().addObjectToFrame(this.renderable3dObject);
    }


    public void setPos(Vector3f pos){
        renderable3dObject.setPos(pos);
    }

    @Override
    public boolean isObsolete() {
        return false;
    }

}
