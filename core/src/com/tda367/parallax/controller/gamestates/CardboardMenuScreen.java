package com.tda367.parallax.controller.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.CardboardCamera;
import com.badlogic.gdx.math.Matrix4;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.CardboardGame;
import com.tda367.parallax.controller.CardboardMenuController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.model.cardboardmenu.CardboardMenu;
import com.tda367.parallax.model.cardboardmenu.MainMenu;
import com.tda367.parallax.model.coreabstraction.RenderManager;
import com.tda367.parallax.model.parallaxcore.Player;
import com.tda367.parallax.view.CardboardMenuRenderer;
import com.tda367.parallax.view.Sound;



public class CardboardMenuScreen implements CardboardScreen {
    private CardboardCamera camera;
    private Player player;
    private CardboardMenuRenderer renderer;
    private CardboardGame game;
    private CardboardMenuController controller;
    private static final float Z_NEAR = 0.1f;
    private static final float Z_FAR = 300.0f;
    private Sound sound;
    private CardboardMenu cbMenu;
    private MainMenu mainMenu;


    public CardboardMenuScreen(CardboardGame game){
        this.game = game;
        mainMenu = new MainMenu(player);
        cbMenu = new CardboardMenu();
        player = new Player();
        camera = new CardboardCamera();
        camera.position.set(0,0,0);
        camera.lookAt(0, 0, -1);
        camera.near = Z_NEAR;
        camera.far = Z_FAR;
        renderer = new CardboardMenuRenderer(camera);
        sound = new Sound();
        controller = new CardboardMenuController(mainMenu,DeviceManager.getGameModeState(game));
    }

    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {
        //mainMenu.update((int) Gdx.graphics.getDeltaTime()*1000);
        if(Gdx.input.justTouched()){
            dispose();
            GameStateManager.setCardboardGameScreen(game);
        }
        camera.update();
        if(cbMenu.getStartButton().isCollided()){
        }else if(cbMenu.getExitButton().isCollided()){
            Gdx.app.exit();
        }

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
        RenderManager.getInstance().getRenderables().clear();
    }
}


