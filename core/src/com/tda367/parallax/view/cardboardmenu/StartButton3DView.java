package com.tda367.parallax.view.cardboardmenu;

import com.tda367.parallax.model.cardboardmenu.StartButton3D;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.parallaxview.View;
import com.tda367.parallax.view.util.Renderable3dObject;
import com.tda367.parallax.view.util.ResourceLoader;


public class StartButton3DView implements View {

    private String internalPath;
    private StartButton3D startButton;
    private Renderable3dObject renderable3dObject;



    StartButton3DView(StartButton3D startButton) {
        internalPath = "3dModels/boxObstacle";
        this.startButton = startButton;
        renderable3dObject = new Renderable3dObject(
                startButton.getPos(),
                startButton.getRot(),
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
        return startButton.collisionActivated();
    }
}
