package com.tda367.parallax.view.cardboardmenu;


import com.tda367.parallax.model.cardboardmenu.CardboardMainMenu;
import com.tda367.parallax.view.Renderer3D;



public class CardboardMainMenuView {

    private final CardboardMainMenu mainMenu;
    private CardboardMenuWorldView world;
    private CardboardMenuExitButtonView exitButton;
    private CardboardMenuStartButtonView startButton;


    public CardboardMainMenuView(CardboardMainMenu mainMenu) {
        this.mainMenu = mainMenu;
        world = new CardboardMenuWorldView(mainMenu.getCardboardWorld());
        exitButton = new CardboardMenuExitButtonView(mainMenu.getExitButton());
        startButton = new CardboardMenuStartButtonView(mainMenu.getStartButton());
    }

    public void render() {
        Renderer3D.getInstance().setCameraPosition(
                mainMenu.getCamera().getPos().getX(),
                mainMenu.getCamera().getPos().getY(),
                mainMenu.getCamera().getPos().getZ()
        );
        world.render();
        exitButton.render();
        startButton.render();
        Renderer3D.getInstance().renderFrame();
    }
}


