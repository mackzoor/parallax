package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.powerups.Container;
import com.tda367.parallax.view.util.Model;
import com.tda367.parallax.view.Renderer3D;

/**
 * View class for {@link com.tda367.parallax.model.parallaxcore.powerups.Container}.
 */
public class ContainerView implements View {

    private final String model3dInternalPath = "3dModels/agelion/agelion.g3db";
    private final Container container;
    private com.tda367.parallax.view.util.Renderable3dObject renderable3dObject;

    ContainerView(Container container) {
        this.container = container;
        renderable3dObject = new com.tda367.parallax.view.util.Renderable3dObject(
                container.getPos(),
                container.getRot(),
                new Model("3dModels/agelion/agelion.g3db"),
                1f
        );
    }

    @Override
    public void render() {
        if (!container.isCollected()){
            Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
        }
    }
    @Override
    public boolean isObsolete() {
        return !container.isCollected();
    }

}
