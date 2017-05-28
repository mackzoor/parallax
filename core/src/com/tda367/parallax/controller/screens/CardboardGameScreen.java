package com.tda367.parallax.controller.screens;

import com.badlogic.gdx.Gdx;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.controllerclasses.game.GameController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.screens.cardboardadapter.CardboardScreenAdapter;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.view.Sound;
import com.tda367.parallax.view.parallaxview.ParallaxView;
import com.tda367.parallax.view.rendering.Renderer3D;

import static com.tda367.parallax.controller.screens.ScreenState.GAME_OVER;

public class CardboardGameScreen extends CardboardScreenAdapter {

    private final Player player;
    private Parallax parallaxGame;
    private GameController controller;
    private final CollisionCalculator collisionCalculator;
    private final Sound sound;
    private ParallaxView parallaxView;
    private final ScreenChanger screenChanger;

    public CardboardGameScreen(Player player, ScreenChanger screenChanger) {
        super();
        this.player = player;
        this.screenChanger = screenChanger;
        this.sound = new Sound();
        this.collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void dispose() {
        CollisionManager.getInstance().getCollidables().clear();
        CollisionManager.getInstance().getObservers().clear();
        this.collisionCalculator.clear();
        this.sound.clearAllActiveMusic();
    }

    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {
        if (this.parallaxGame.isGameOver()) {
            gameOver();
        } else {
            this.parallaxGame.update((int) (Gdx.graphics.getDeltaTime() * 1000));
            this.collisionCalculator.run();
        }
    }

    @Override
    public void onDrawEye(Eye eye) {
        Renderer3D.getInstance().onDrawEye(eye);
        this.parallaxView.render();
    }

    public void newGame() {
        this.player.setSpaceCraft(SpaceCraftFactory.getAgelionInstance(15));
        this.parallaxGame = new Parallax(this.player);
        this.parallaxView = new ParallaxView(this.parallaxGame, true, false);
        this.controller = new GameController(this.parallaxGame, this.parallaxView, DeviceManager.getDevice());
    }

    private void gameOver() {
        this.dispose();
        this.screenChanger.requestScreenChange(GAME_OVER, this.player);
    }

}
