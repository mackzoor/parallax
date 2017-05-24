package com.tda367.parallax.controller.gamescreens;

import com.badlogic.gdx.Gdx;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.controllerclasses.CardboardMenuController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardScreenAdapter;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.cardboardmenu.MainMenu;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.utilities.MathUtilities;
import com.tda367.parallax.view.rendering.Renderer3D;
import com.tda367.parallax.view.Sound;
import com.tda367.parallax.view.cardboardmenu.MainMenuView;

import javax.vecmath.Vector3f;


public class CardboardMenuScreen extends CardboardScreenAdapter {
    private CardboardMenuController controller;
    private Sound sound;
    private MainMenu mainMenu;
    private CollisionCalculator collisionCalculator;
    private MainMenuView view;
    private Player player;

    public CardboardMenuScreen(Player player) {
        this.player = player;
        mainMenu = new MainMenu();
        sound = new Sound();
        controller = new CardboardMenuController(mainMenu, DeviceManager.getDevice());
        view = new MainMenuView(mainMenu,true);
        collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {
        if (mainMenu.getStartButton().isCollided()){
            startButtonHit();
        } else if (mainMenu.getExitButton().isCollided()) {
            exitButtonHit();
        } else {
            mainMenu.update((int) (Gdx.graphics.getDeltaTime() * 1000));
            collisionCalculator.run();
            mainMenu.setAimDirection(getLookDirection(paramHeadTransform));
        }
    }

    @Override
    public void onDrawEye(Eye paramEye) {
        // Apply the eye transformation to the camera
        Renderer3D.getInstance().onDrawEye(paramEye);
        view.render();
        //Renders scene for current eye

    }

    @Override
    public void dispose() {
        CollisionManager.getInstance().getCollidables().clear();
        //TODO Fix to work with new render structure
//        RenderQueue.getInstance().getRenderables().clear();
        sound.clearAllActiveMusic();
    }

    private void startButtonHit() {
        dispose();
        GameStateManager.setCardboardGameScreen(player);
    }

    private void exitButtonHit() {
        Gdx.app.exit();
    }

    private Vector3f getLookDirection(HeadTransform paramHeadTransForm) {
        float[] eulerAngles = new float[3];
        paramHeadTransForm.getEulerAngles(eulerAngles, 0);
        Vector3f returnVector = MathUtilities.eulerToVector(eulerAngles[0],eulerAngles[2],eulerAngles[1]);
        returnVector.normalize();
        return returnVector;
    }
}


