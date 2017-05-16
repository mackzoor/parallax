package com.tda367.parallax.view.cardboardmenu;

import com.tda367.parallax.model.cardboardmenu.CardboardStartButton;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.parallaxview.View;
import com.tda367.parallax.view.util.Renderable3dObject;



public class CardboardMenuStartButtonView implements View {

    private String internalPath;
    private CardboardStartButton startButton;
    private Renderable3dObject renderable3dObject;



    CardboardMenuStartButtonView(CardboardStartButton startButton) {
        internalPath = "3dModels/boxObstacle";
        this.startButton = startButton;
        renderable3dObject = new Renderable3dObject(startButton.getPos(),
                startButton.getRot(),
                new com.tda367.parallax.view.util.Model("3dModels/boxObstacle/boxObstacle.g3db"
                ),0.5f);
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
