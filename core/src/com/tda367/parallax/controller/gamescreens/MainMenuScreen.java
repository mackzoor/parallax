package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tda367.parallax.controller.gamecontrollers.MainMenuController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.model.menu.MainMenuModel;
import com.tda367.parallax.view.menu.MainMenuView;

public class MainMenuScreen implements Screen {

    private Game game;
    private MainMenuModel mainMenuModel;
    private MainMenuController mainMenuController;
    private MainMenuView mainMenuView;


    public MainMenuScreen(final Game game) {
        this.game = game;
        this.mainMenuModel = new MainMenuModel(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.mainMenuController = new MainMenuController(mainMenuModel, DeviceManager.getDevice());
        this.mainMenuView = new MainMenuView(mainMenuModel);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (mainMenuController.isStartButtonPressed()) {
            GameStateManager.setGameScreen(game);
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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mainMenuView.dispose();
    }
}
