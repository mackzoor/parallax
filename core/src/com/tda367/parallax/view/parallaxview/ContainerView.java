package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.powerups.Container;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.util.Renderable3dObject;
import com.tda367.parallax.util.ResourceLoader;

/**
 * View class for {@link com.tda367.parallax.model.parallaxcore.powerups.Container}.
 */
public class ContainerView implements View {

    private final String model3dInternalPath = "3dModels/mysterycontainer/mysterycontainer.g3db";
    private final Container container;
    private Renderable3dObject renderable3dObject;

    /**
     * Creates a ContainerView from a {@link Container}.
     * @param container to be used to create the ContainerView.
     */
    ContainerView(Container container) {
        this.container = container;
        renderable3dObject = new Renderable3dObject(
                container.getPos(),
                container.getRot(),
                ResourceLoader.getInstance().getModel(model3dInternalPath),
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
