package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.menu;

import com.tda367.parallax.model.menu.button3d.StartButton3D;
import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.View;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

public class StartButton3DView implements View {

    private static final String INTERNAL_PATH = "3dModels/playtext/play.g3db";
    private final StartButton3D startButton;
    private final Renderable3dObject renderable3dObject;


    public StartButton3DView(StartButton3D startButton) {
        this.startButton = startButton;
        this.renderable3dObject = new Renderable3dObject(
                startButton.getPos(),
                startButton.getRot(),
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
        return this.startButton.collisionActivated();
    }
}
