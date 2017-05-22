package com.tda367.parallax.view.cardboardmenu;

import com.tda367.parallax.model.cardboardmenu.StartButton3D;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.parallaxview.View;
import com.tda367.parallax.view.util.Renderable3dObject;
import com.tda367.parallax.util.ResourceLoader;


public class StartButton3DView implements View {

    private final static String INTERNAL_PATH = "3dModels/boxObstacle";
    private StartButton3D startButton;
    private Renderable3dObject renderable3dObject;



    StartButton3DView(StartButton3D startButton) {
        this.startButton = startButton;
        renderable3dObject = new Renderable3dObject(
                startButton.getPos(),
                startButton.getRot(),
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
        return startButton.collisionActivated();
    }
}
