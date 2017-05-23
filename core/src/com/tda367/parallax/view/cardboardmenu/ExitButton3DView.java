package com.tda367.parallax.view.cardboardmenu;

import com.tda367.parallax.model.cardboardmenu.ExitButton3D;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.parallaxview.View;
import com.tda367.parallax.view.util.Renderable3dObject;
import com.tda367.parallax.utilities.ResourceLoader;


public class ExitButton3DView implements View {

    ExitButton3D exitButton;
    private final static String INTERNAL_PATH = "3dModels/boxObstacle/boxObstacle.g3db";
    private Renderable3dObject renderable3dObject;


    ExitButton3DView(ExitButton3D exitButton) {
        this.exitButton = exitButton;
        renderable3dObject = new Renderable3dObject(
                exitButton.getPos(),
                exitButton.getRot(),
                ResourceLoader.getInstance().getModel(INTERNAL_PATH),
                0.5f
        );
    }


    @Override
    public void render() {
        Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
    }

    @Override
    public boolean isObsolete() {
        return exitButton.collisionActivated();
    }
}
