package com.tda367.parallax.controller.screens;

import com.badlogic.gdx.Gdx;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.controllerclasses.game.GameController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.view.Sound;
import com.tda367.parallax.view.parallaxview.ParallaxView;
import com.tda367.parallax.view.rendering.Renderer3D;

public class CardboardGameScreen extends com.tda367.parallax.controller.screens.cardboardadapter.CardboardScreenAdapter {

    private Player player;
    private Parallax parallaxGame;
    private GameController controller;
    private CollisionCalculator collisionCalculator;
    private Sound sound;
    private ParallaxView parallaxView;
    private ScreenChanger screenChanger;

    public CardboardGameScreen(Player player, ScreenChanger screenChanger) {
        //Gdx.graphics.setTitle("Galactica space wars of justice, ultimate edition");
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
        //Updates Parallax game logic
        this.parallaxGame.update((int) (Gdx.graphics.getDeltaTime() * 1000));
        this.collisionCalculator.run();

    }

    @Override
    public void onDrawEye(Eye eye) {
        // Apply the eye transformation to the camera.
        Renderer3D.getInstance().onDrawEye(eye);
        //Renders scene for current eye
        this.parallaxView.render();
    }

    public void newGame() {
        this.player.addSpaceCraft(SpaceCraftFactory.getAgelionInstance(15));
        this.parallaxGame = new Parallax(this.player);
        this.parallaxView = new ParallaxView(this.parallaxGame, false);
        this.controller = new GameController(this.parallaxGame, this.parallaxView, DeviceManager.getDevice());
    }

}
