package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.tda367.parallax.controller.controllerclasses.MainMenuController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.model.menu.MainMenuModel;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.view.menu.MainMenuView;

public class MainMenuScreen extends ScreenAdapter {

    private Game game;
    private Player player;
    private MainMenuModel mainMenuModel;
    private MainMenuController mainMenuController;
    private MainMenuView mainMenuView;


    public MainMenuScreen(final Game game, Player player) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void render(float delta) {
        if (mainMenuController.isStartButtonPressed()) {
            GameStateManager.setGameScreen(game,player);
        } else if (mainMenuController.isExitButtonPressed()) {
            Gdx.app.exit();
        } else {
            mainMenuView.render();
        }
    }

    @Override
    public void resize(int width, int height) {
        mainMenuModel.resize(width, height);
    }

    @Override
    public void dispose() {
        //mainMenuController = null;
        mainMenuView.dispose();
    }

    public void newMainMenu(){
        this.mainMenuModel = new MainMenuModel(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.mainMenuController = new MainMenuController(mainMenuModel, DeviceManager.getDevice());
        this.mainMenuView = new MainMenuView(mainMenuModel);
    }
}
