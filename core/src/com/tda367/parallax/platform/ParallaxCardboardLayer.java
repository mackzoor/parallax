package com.tda367.parallax.platform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.CardBoardApplicationListener;
import com.badlogic.gdx.backends.android.CardboardCamera;
import com.badlogic.gdx.math.Matrix4;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;
import com.tda367.parallax.controller.GameController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.model.parallaxcore.Parallax;
import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.model.parallaxcore.spacecraft.Agelion;
import com.tda367.parallax.view.Renderer;
import com.tda367.parallax.view.Sound;

/**
 * Created by Markus on 2017-04-11.
 */
public class ParallaxCardboardLayer implements CardBoardApplicationListener {

    private CardboardCamera camera;
    private Player player;
    private Parallax parallaxGame;
    private Renderer renderer;
    private GameController controller;
    private Device device;
    private static final float Z_NEAR = 0.1f;
    private static final float Z_FAR = 300.0f;
    private Sound sound;

    @Override
    public void create() {

        Gdx.graphics.setTitle("Galactica space wars of justice, ultimate edition");

        // Initiate game with space craft "Agelion"
        this.player = new Player();
        this.player.addSpaceCraft(new Agelion(10));
        this.parallaxGame = new Parallax(player);
        this.device = DeviceManager.getGameModeState(this);
        controller = new GameController(parallaxGame, device);

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
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {
        //Updates Parallax game logic
        parallaxGame.update((int)(Gdx.graphics.getDeltaTime() * 1000));

        //Updates camera
        camera.position.set(
                parallaxGame.getCamera().getPos().getX(),
                parallaxGame.getCamera().getPos().getZ(),
                parallaxGame.getCamera().getPos().getY()*-1
        );
        camera.update();
        device.update();
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

    @Override
    public void onFinishFrame(Viewport paramViewport) {

    }

    @Override
    public void onRendererShutdown() {

    }

    @Override
    public void onCardboardTrigger() {

    }
}
