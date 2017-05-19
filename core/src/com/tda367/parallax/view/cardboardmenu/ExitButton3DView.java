package com.tda367.parallax.view.cardboardmenu;

import com.tda367.parallax.model.cardboardmenu.ExitButton3D;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.parallaxview.View;
import com.tda367.parallax.view.util.ResourceLoader;


public class ExitButton3DView implements View {

    ExitButton3D exitButton;
    private String internalPath;
    private com.tda367.parallax.view.util.Renderable3dObject renderable3dObject;


    ExitButton3DView(ExitButton3D exitButton) {
        internalPath = "3dModels/boxObstacle/boxObstacle.g3db";
        this.exitButton = exitButton;
        renderable3dObject = new com.tda367.parallax.view.util.Renderable3dObject(
                exitButton.getPos(),
                exitButton.getRot(),
                ResourceLoader.getInstance().getModel("3dModels/boxObstacle/boxObstacle.g3db"),
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
