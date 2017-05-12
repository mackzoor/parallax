package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.CardboardCamera;
import com.badlogic.gdx.math.Matrix4;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.gamecontrollers.CardboardMenuController;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardGame;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardScreen;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.cardboardmenu.CardboardMainMenu;
import com.tda367.parallax.model.cardboardmenu.CardboardMenuObserver;
import com.tda367.parallax.model.coreabstraction.RenderQueue;
import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;
import com.tda367.parallax.view.CardboardMenuRenderer;
import com.tda367.parallax.view.Renderer;
import com.tda367.parallax.view.Sound;


public class CardboardMenuScreen implements CardboardScreen, CardboardMenuObserver {
    private CardboardCamera camera;
    private Player player;
    private CardboardMenuRenderer renderer;
    private CardboardGame game;
    private CardboardMenuController controller;
    private static final float Z_NEAR = 0.1f;
    private static final float Z_FAR = 300.0f;
    private Sound sound;
    private CardboardMainMenu cardboardMainMenu;
    private CollisionCalculator collisionCalculator;


    public CardboardMenuScreen(CardboardGame game) {
        this.game = game;
        cardboardMainMenu = new CardboardMainMenu();
        player = new Player();
        camera = new CardboardCamera();
        camera.position.set(0, 0, 0);
        camera.lookAt(0, 0, -1);
        camera.near = Z_NEAR;
        camera.far = Z_FAR;
        renderer = new CardboardMenuRenderer(camera);
        sound = new Sound();
        controller = new CardboardMenuController(cardboardMainMenu, DeviceManager.getGameModeState(game));
        cardboardMainMenu.addObservers(this);
        collisionCalculator = new CollisionCalculator();
    }
    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {
        cardboardMainMenu.update((int) (Gdx.graphics.getDeltaTime() * 1000));
        camera.update();
        collisionCalculator.run();
    }


    @Override
    public void onDrawEye(Eye paramEye) {
        // Apply the eye transformation to the camera.
        camera.setEyeViewAdjustMatrix(new Matrix4(paramEye.getEyeView()));

        float[] perspective = paramEye.getPerspective(Z_NEAR, Z_FAR);
        camera.setEyeProjection(new Matrix4(perspective));
        camera.update();

        //Renders scene for current eye
        renderer.renderAll();
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
        CollisionManager.getInstance().getCollidables().clear();
        RenderQueue.getInstance().getRenderables().clear();
        sound.clearAllActiveMusic();
    }

    @Override
    public void cardboardStartButtonAction() {
        dispose();
        GameStateManager.setCardboardGameScreen(game);
    }

    @Override
    public void cardboardExitButtonAction() {
        Gdx.app.exit();
    }
}


