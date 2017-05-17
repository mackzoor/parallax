package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.spacecraft.ISpaceCraft;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.util.Renderable3dObject;
import com.tda367.parallax.view.util.ResourceLoader;

/**
 * View class for the spacecraft {@link com.tda367.parallax.model.parallaxcore.spacecraft.Agelion}
 */
public class AgelionView implements View {

    private final String model3dInternalPath = "3dModels/agelion/agelion.g3db";
    private final ISpaceCraft agelion;
    private Renderable3dObject renderable3dObject;

    public AgelionView(ISpaceCraft agelion) {
        this.agelion = agelion;
        if (!isObsolete()){
            renderable3dObject = new Renderable3dObject(
                    agelion.getPos(),
                    agelion.getRot(),
                    ResourceLoader.getInstance().getModel(model3dInternalPath),
                    1f
            );
        }
    }

    @Override
    public void render() {
        if (!isObsolete()){
            renderable3dObject.setPos(agelion.getPos());
            renderable3dObject.setRot(agelion.getRot());
            Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
        }
    }

    @Override
    public boolean isObsolete() {
        return agelion.getHealth() < 1;
    }
}