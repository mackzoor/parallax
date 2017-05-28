package com.tda367.parallax.controller.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.tda367.parallax.controller.controllerclasses.game.GameController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.model.externalcollsioncalculators.CollisionCalculator;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.view.sound.Sound;
import com.tda367.parallax.view.parallaxview.ParallaxView;

import static com.tda367.parallax.controller.screens.ScreenState.GAME_OVER;

public class GameScreen extends ScreenAdapter {
    private final static int SEC_TO_MILLISEC = 1000;
    private final static int START_SPEED = 15;
    private final Player player;
    private Parallax parallaxGame;
    private GameController controller;
    private final CollisionCalculator collisionCalculator;
    private ParallaxView parallaxView;
    private final ScreenChanger screenChanger;
    private final boolean particlesEnabled;

    public GameScreen(Player player, ScreenChanger screenChanger, boolean particlesEnabled) {
        super();
        this.particlesEnabled = particlesEnabled;
        this.player = player;
        this.screenChanger = screenChanger;
        this.collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void render(float delta) {
        if (this.parallaxGame.isGameOver()) {
            gameOver();
        } else {
            this.parallaxGame.update((int) (delta * SEC_TO_MILLISEC));
            this.collisionCalculator.run();
            this.parallaxView.render();
            DeviceManager.getDevice().update();
        }
    }

    @Override
    public void resize(int width, int height) {
        this.parallaxView.setWidth(width);
        this.parallaxView.setHeight(height);
    }

    @Override
    public void dispose() {
        CollisionManager.getInstance().getCollidables().clear();
        Controllers.clearListeners();
        Sound.getInstance().clearAllActiveMusic();
        this.collisionCalculator.clear();
        CollisionManager.getInstance().getObservers().clear();
    }

    public void newGame() {
        this.player.setSpaceCraft(SpaceCraftFactory.getAgelionInstance(START_SPEED));
        this.parallaxGame = new Parallax(this.player);
        this.parallaxView = new ParallaxView(this.parallaxGame, false, this.particlesEnabled);
        this.controller = new GameController(this.parallaxGame,
                this.parallaxView,
                DeviceManager.getDevice());
    }

    private void gameOver() {
        this.dispose();
        this.screenChanger.requestScreenChange(GAME_OVER, this.player);
    }
}
