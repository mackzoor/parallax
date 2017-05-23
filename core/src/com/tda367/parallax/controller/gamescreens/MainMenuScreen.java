package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.tda367.parallax.controller.controllerclasses.MainMenuController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.model.cardboardmenu.MainMenu;
import com.tda367.parallax.model.menu.MainMenuModel;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.view.Sound;
import com.tda367.parallax.view.cardboardmenu.MainMenuView;

public class MainMenuScreen extends ScreenAdapter {

    private Game game;
    private Player player;
    private MainMenu model;
    private MainMenuController controller;
    private MainMenuView view;
    private Sound sound;


    public MainMenuScreen(final Game game, Player player) {
        this.player = player;
        this.game = game;
        sound = new Sound();
    }

    @Override
    public void render(float delta) {
        if (controller.isStartButtonPressed()) {
            GameStateManager.setGameScreen(game,player);
        } else if (controller.isExitButtonPressed()) {
            Gdx.app.exit();
        } else {
            view.render();
        }
    }

    @Override
    public void resize(int width, int height) {
        view.setWidth(width);
        view.setHeight(height);
    }

    @Override
    public void dispose() {
        //mainMenuController = null;
    }

    public void newMainMenu(){
        this.model = new MainMenu();
        this.controller = new MainMenuController(model, DeviceManager.getDevice());
        this.view = new MainMenuView(model,false);
    }
}
