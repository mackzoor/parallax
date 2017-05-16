package com.tda367.parallax.view.cardboardmenu;

import com.tda367.parallax.model.cardboardmenu.CardboardExitButton;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.parallaxview.View;


public class CardboardMenuExitButtonView implements View {

    CardboardExitButton exitButton;
    private String internalPath;
    private com.tda367.parallax.view.util.Renderable3dObject renderable3dObject;


    CardboardMenuExitButtonView(CardboardExitButton exitButton) {
        internalPath = "3dModels/boxObstacle";
        this.exitButton = exitButton;
        renderable3dObject = new com.tda367.parallax.view.util.Renderable3dObject(
                exitButton.getPos(),
                exitButton.getRot(),
                new com.tda367.parallax.view.util.Model("3dModels/boxObstacle/boxObstacle.g3db"
                ),0.5f);
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
