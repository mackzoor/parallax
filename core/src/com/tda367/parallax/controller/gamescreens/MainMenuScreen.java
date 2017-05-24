package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.tda367.parallax.controller.controllerclasses.MainMenuController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.cardboardmenu.MainMenu;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.view.Sound;
import com.tda367.parallax.view.cardboardmenu.MainMenuView;

public class MainMenuScreen extends ScreenAdapter {

    private Player player;
    private MainMenu model;
    private MainMenuController controller;
    private MainMenuView view;
    private Sound sound;
    private CollisionCalculator collisionCalculator;
    private AudioQueue audioQueue;


    public MainMenuScreen(Player player) {
        this.player = player;
        sound = new Sound();
        audioQueue = AudioQueue.getInstance();
        collisionCalculator = new CollisionCalculator();

    }

    @Override
    public void render(float delta) {
        if (model.getStartButton().isCollided()) {
            dispose();
            GameStateManager.setGameScreen(player);
        } else if (model.getExitButton().isCollided()) {
            dispose();
            Gdx.app.exit();
        }
        model.update((int) (Gdx.graphics.getDeltaTime() * 1000));
        view.render();
        collisionCalculator.run();
    }


    @Override
    public void resize(int width, int height) {
        view.setWidth(width);
        view.setHeight(height);
    }

    @Override
    public void dispose() {
        CollisionManager.getInstance().getCollidables().clear();
        Controllers.clearListeners();
        collisionCalculator.clear();
        CollisionManager.getInstance().getObservers().clear();
    }

    public void newMainMenu() {
        this.model = new MainMenu();
        this.controller = new MainMenuController(model, DeviceManager.getDevice());
        this.view = new MainMenuView(model, false);
    }
}
