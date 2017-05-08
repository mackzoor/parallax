package com.tda367.parallax.controller.gamestates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tda367.parallax.controller.MainMenuController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.model.menu.MainMenuModel;
import com.tda367.parallax.view.MainMenuView;

/**
 * Created by Markus on 2017-05-04.
 */

public class MainMenuScreen implements Screen {

    private Game game;
    private MainMenuModel mainMenuModel;
    private MainMenuController mainMenuController;
    private MainMenuView mainMenuView;


    public MainMenuScreen(final Game game) {
        this.game = game;
        this.mainMenuModel = new MainMenuModel(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.mainMenuController = new MainMenuController(mainMenuModel, DeviceManager.getDevice(), game);
        this.mainMenuView = new MainMenuView(mainMenuModel);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        mainMenuView.render();
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
