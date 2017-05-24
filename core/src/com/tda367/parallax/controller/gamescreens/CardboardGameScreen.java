package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Gdx;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.controller.controllerclasses.game.GameController;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardGame;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardScreenAdapter;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.view.rendering.Renderer3D;
import com.tda367.parallax.view.Sound;
import com.tda367.parallax.view.parallaxview.ParallaxView;

public class CardboardGameScreen extends CardboardScreenAdapter {

    private Player player;
    private Parallax parallaxGame;
    private GameController controller;
    private CollisionCalculator collisionCalculator;
    private Sound sound;
    private ParallaxView parallaxView;

    public CardboardGameScreen(Player player){
        //Gdx.graphics.setTitle("Galactica space wars of justice, ultimate edition");
        // Initiate game with space craft "Agelion"

        this.player = player;
        this.player.addSpaceCraft(SpaceCraftFactory.getAgelionInstance(10));
        this.parallaxGame = new Parallax(player);
        parallaxView = new ParallaxView(parallaxGame,true);
        controller = new GameController(parallaxGame, parallaxView, DeviceManager.getDevice());
        sound = new Sound();
        collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void dispose() {
        CollisionManager.getInstance().getCollidables().clear();
        CollisionManager.getInstance().getObservers().clear();
        collisionCalculator.clear();
        sound.clearAllActiveMusic();
    }

    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {
        //Updates Parallax game logic
        parallaxGame.update((int)(Gdx.graphics.getDeltaTime() * 1000));
        collisionCalculator.run();

    }

    @Override
    public void onDrawEye(Eye eye) {
        // Apply the eye transformation to the camera.
        Renderer3D.getInstance().onDrawEye(eye);
        //Renders scene for current eye
        parallaxView.render();
    }

}
