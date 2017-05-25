package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.controller.controllerclasses.MainMenuController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.model.menu.MainMenu;
import com.tda367.parallax.view.Sound;
import com.tda367.parallax.view.menu.MainMenuView;

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
        this.sound = new Sound();
        this.audioQueue = AudioQueue.getInstance();
        this.collisionCalculator = new CollisionCalculator();

    }

    @Override
    public void render(float delta) {
        if (this.model.getStartButton().isCollided()) {
            dispose();
            GameStateManager.setGameScreen(this.player);
        } else if (this.model.getExitButton().isCollided()) {
            dispose();
            Gdx.app.exit();
        }
        this.model.update((int) (Gdx.graphics.getDeltaTime() * 1000));
        this.view.render();
        this.collisionCalculator.run();
    }


    @Override
    public void resize(int width, int height) {
        this.view.setWidth(width);
        this.view.setHeight(height);
    }

    @Override
    public void dispose() {
        CollisionManager.getInstance().getCollidables().clear();
        Controllers.clearListeners();
        this.collisionCalculator.clear();
        CollisionManager.getInstance().getObservers().clear();
    }

    public void newMainMenu() {
        this.model = new MainMenu();
        this.controller = new MainMenuController(this.model, DeviceManager.getDevice());
        this.view = new MainMenuView(this.model, false);
    }
}
