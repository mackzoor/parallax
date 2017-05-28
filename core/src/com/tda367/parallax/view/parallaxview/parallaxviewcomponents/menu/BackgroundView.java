package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.menu;

import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * A background scenery 3d model for the menu.
 */
public class BackgroundView implements View {


    private static final String INTERNAL_PATH = "3dModels/floor/floor.g3db";
    private final Renderable3dObject renderable3dObject;

    public BackgroundView() {
        this.renderable3dObject = new Renderable3dObject(
                new Vector3f(),
                new Quat4f(),
                ResourceLoader.getInstance().getModel(this.INTERNAL_PATH),
                1f);
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
