package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.powerups.Container;
import com.tda367.parallax.model.util.Model;
import com.tda367.parallax.view.Renderer3D;

/**
 * View class for {@link com.tda367.parallax.model.parallaxcore.powerups.Container}.
 */
public class ContainerView implements View {

    private final String model3dInternalPath = "3dModels/agelion/agelion.g3db";
    private final Container obstacle;
    private Renderable3dObject renderable3dObject;

    ContainerView(Container obstacle) {
        this.obstacle = obstacle;
        renderable3dObject = new Renderable3dObject(
                obstacle.getPos(),
                obstacle.getRot(),
                new Model("3dModels/agelion/agelion.g3db")
        );
    }

    @Override
    public void render() {
        Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
    }
    @Override
    public boolean isObsolete() {
        return !obstacle.isCollected();
    }

}
