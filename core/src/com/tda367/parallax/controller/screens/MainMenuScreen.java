package com.tda367.parallax.controller.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.tda367.parallax.controller.controllerclasses.MainMenuController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.model.externalcollsioncalculators.CollisionCalculator;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.menu.MainMenu;
import com.tda367.parallax.view.parallaxview.MainMenuView;

import static com.tda367.parallax.controller.screens.ScreenState.GAME;

public class MainMenuScreen extends ScreenAdapter {
    private static final int SEC_TO_MILLISEC = 1000;
    private final Player player;
    private MainMenu model;
    private MainMenuController controller;
    private MainMenuView view;
    private final CollisionCalculator collisionCalculator;
    private final ScreenChanger screenChanger;
    private final boolean particlesEnabled;

    public MainMenuScreen(Player player, ScreenChanger screenChanger, boolean particlesEnabled) {
        super();
        this.particlesEnabled = particlesEnabled;
        this.player = player;
        this.screenChanger = screenChanger;
        this.collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void render(float delta) {
        if (this.model.getStartButton().isCollided()) {
            dispose();
            this.screenChanger.requestScreenChange(GAME, this.player);
        } else if (this.model.getExitButton().isCollided()) {
            dispose();
            Gdx.app.exit();
        }
        this.model.update((int) (delta * SEC_TO_MILLISEC));
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
        this.view = new MainMenuView(this.model, false, this.particlesEnabled);
    }
}
