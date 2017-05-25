package com.tda367.parallax.view.menu;

import com.tda367.parallax.model.menu.button3d.ExitButton3D;
import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.parallaxview.View;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;


public class ExitButton3DView implements View {

    private ExitButton3D exitButton;
    private final static String INTERNAL_PATH = "3dModels/quittext/quit.g3db";
    private Renderable3dObject renderable3dObject;


    ExitButton3DView(ExitButton3D exitButton) {
        this.exitButton = exitButton;
        this.renderable3dObject = new Renderable3dObject(
                exitButton.getPos(),
                exitButton.getRot(),
                ResourceLoader.getInstance().getModel(INTERNAL_PATH),
                0.5f
        );
    }


    @Override
    public void render() {
        Renderer3D.getInstance().addObjectToFrame(this.renderable3dObject);
    }

    @Override
    public boolean isObsolete() {
        return this.exitButton.collisionActivated();
    }
}