package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.spacecraft.ISpaceCraft;
import com.tda367.parallax.view.util.Model;
import com.tda367.parallax.view.Renderer3D;

/**
 * View class for the spacecraft {@link com.tda367.parallax.model.parallaxcore.spacecraft.Agelion}
 */
public class AgelionView implements View {

    private final String model3dInternalPath = "3dModels/agelion/agelion.g3db";
    private final ISpaceCraft agelion;
    private com.tda367.parallax.view.util.Renderable3dObject renderable3dObject;

    public AgelionView(ISpaceCraft agelion) {
        this.agelion = agelion;
        renderable3dObject = new com.tda367.parallax.view.util.Renderable3dObject(
                agelion.getPos(),
                agelion.getRot(),
                new Model("3dModels/agelion/agelion.g3db"),
                1f
        );
    }

    @Override
    public void render() {
        Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
    }

    @Override
    public boolean isObsolete() {
        return agelion.getHealth() < 1;
    }
}
