package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.menu;

import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * A renderable 3d model text.
 */
public class GalacticaText implements View {

    private static final String INTERNAL_PATH = "3dModels/galacticatext/galactica.g3db";
    private static final Vector3f TEXT_POSITION_OFFSET = new Vector3f(0,4,3);
    private static final float OPACITY = 1f;

    private final Renderable3dObject galacticaText;


    public GalacticaText() {
        this.galacticaText = new Renderable3dObject(
                TEXT_POSITION_OFFSET,
                new Quat4f(),
                ResourceLoader.getInstance().getModel(INTERNAL_PATH),
                OPACITY
        );
    }


    @Override
    public void render() {
        Renderer3D.getInstance().addObjectToFrame(this.galacticaText);
    }

    @Override
    public boolean isObsolete() {
        return false;
    }

}
