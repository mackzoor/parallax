package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.CardboardCamera;
import com.badlogic.gdx.math.Matrix4;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.gamecontrollers.GameController;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardGame;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardScreen;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardScreenAdapter;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.parallaxcore.GameOverException;
import com.tda367.parallax.model.parallaxcore.Parallax;
import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.model.parallaxcore.spacecraft.Agelion;
import com.tda367.parallax.model.parallaxcore.spacecraft.SpaceCraftFactory;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.Sound;
import com.tda367.parallax.view.parallaxview.ParallaxView;

public class CardboardGameScreen extends CardboardScreenAdapter {

    private CardboardGame game;
    private Player player;
    private Parallax parallaxGame;
    private GameController controller;
    private CollisionCalculator collisionCalculator;
    private Sound sound;
    private ParallaxView parallaxView;

    public CardboardGameScreen(CardboardGame game){
        this.game = game;
        //Gdx.graphics.setTitle("Galactica space wars of justice, ultimate edition");
        // Initiate game with space craft "Agelion"
        this.player = new Player();
        this.player.addSpaceCraft(SpaceCraftFactory.getAgelionInstance(10));
        this.parallaxGame = new Parallax(player);
        controller = new GameController(parallaxGame, DeviceManager.getGameModeState(game));
        parallaxView = new ParallaxView(parallaxGame,true);
        sound = new Sound();
        collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void dispose() {
        //TODO Fix to work with new render structure
//        RenderQueue.getInstance().getRenderables().clear();
    }

    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {
        //Updates Parallax game logic
        try {
            parallaxGame.update((int)(Gdx.graphics.getDeltaTime() * 1000));
        } catch (GameOverException gameOverException) {
            System.out.println("You're dead to me");
        }
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
