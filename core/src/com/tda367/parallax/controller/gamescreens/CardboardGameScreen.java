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
import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.coreabstraction.RenderQueue;
import com.tda367.parallax.model.parallaxcore.Parallax;
import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.model.parallaxcore.spacecraft.Agelion;
import com.tda367.parallax.view.Renderer;
import com.tda367.parallax.view.Sound;

public class CardboardGameScreen implements CardboardScreen {

    private CardboardGame game;
    private CardboardCamera camera;
    private Player player;
    private Parallax parallaxGame;
    private Renderer renderer;
    private GameController controller;
    private static final float Z_NEAR = 0.1f;
    private static final float Z_FAR = 300.0f;
    private CollisionCalculator collisionCalculator;
    private Sound sound;

    public CardboardGameScreen(CardboardGame game){
        this.game = game;
        Gdx.graphics.setTitle("Galactica space wars of justice, ultimate edition");
        // Initiate game with space craft "Agelion"
        this.player = new Player();
        this.player.addSpaceCraft(new Agelion(10));
        this.parallaxGame = new Parallax(player);
        controller = new GameController(parallaxGame, DeviceManager.getGameModeState(game));

        // Setup of special camera for VR
        camera = new CardboardCamera();
        camera.position.set(
                parallaxGame.getCamera().getPos().getX(),
                parallaxGame.getCamera().getPos().getY(),
                parallaxGame.getCamera().getPos().getZ()
        );
        camera.lookAt(0, 0, -1);
        camera.near = Z_NEAR;
        camera.far = Z_FAR;

        renderer = new Renderer(camera);
        sound = new Sound();
        collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
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
        RenderQueue.getInstance().getRenderables().clear();
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
        camera.setEyeViewAdjustMatrix(new Matrix4(eye.getEyeView()));

        float[] perspective = eye.getPerspective(Z_NEAR, Z_FAR);
        camera.setEyeProjection(new Matrix4(perspective));
        camera.update();

        //Renders scene for current eye
        renderer.renderAll();
    }
}
