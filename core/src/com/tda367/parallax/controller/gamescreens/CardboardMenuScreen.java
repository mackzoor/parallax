package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Gdx;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.controllerclasses.CardboardMenuController;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardGame;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardScreenAdapter;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.cardboardmenu.MainMenu;
import com.tda367.parallax.model.cardboardmenu.CardboardMenuObserver;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.view.rendering.Renderer3D;
import com.tda367.parallax.view.Sound;
import com.tda367.parallax.view.cardboardmenu.CardboardMainMenuView;


public class CardboardMenuScreen extends CardboardScreenAdapter implements CardboardMenuObserver {
    private CardboardGame game;
    private CardboardMenuController controller;
    private Sound sound;
    private MainMenu mainMenu;
    private CollisionCalculator collisionCalculator;
    private CardboardMainMenuView view;

    public CardboardMenuScreen(CardboardGame game) {
        this.game = game;
        mainMenu = new MainMenu();
        sound = new Sound();
        controller = new CardboardMenuController(mainMenu, DeviceManager.getGameModeState(game));
        view = new CardboardMainMenuView(mainMenu,true);

        mainMenu.addObservers(this);
        collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {
        mainMenu.update((int) (Gdx.graphics.getDeltaTime() * 1000));
        collisionCalculator.run();

    }

    @Override
    public void onDrawEye(Eye paramEye) {
        // Apply the eye transformation to the camera
        Renderer3D.getInstance().onDrawEye(paramEye);
        view.render();
        //Renders scene for current eye

    }

    @Override
    public void dispose() {
        CollisionManager.getInstance().getCollidables().clear();
        //TODO Fix to work with new render structure
//        RenderQueue.getInstance().getRenderables().clear();
        sound.clearAllActiveMusic();
    }

    @Override
    public void cardboardStartButtonAction() {
        dispose();
        GameStateManager.setCardboardGameScreen(game);
    }

    @Override
    public void cardboardExitButtonAction() {
        Gdx.app.exit();
    }
}


