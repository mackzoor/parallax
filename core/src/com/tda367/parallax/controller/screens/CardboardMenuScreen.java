package com.tda367.parallax.controller.screens;

import com.badlogic.gdx.Gdx;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.controllerclasses.CardboardMenuController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.screens.cardboardadapter.CardboardScreenAdapter;
import com.tda367.parallax.model.CollisionCalculator;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.menu.MainMenu;
import com.tda367.parallax.utilities.MathUtilities;
import com.tda367.parallax.view.Sound;
import com.tda367.parallax.view.menu.MainMenuView;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Vector3f;

import static com.tda367.parallax.controller.screens.ScreenState.GAME;


public class CardboardMenuScreen extends CardboardScreenAdapter {
    private CardboardMenuController controller;
    private Sound sound;
    private MainMenu mainMenu;
    private CollisionCalculator collisionCalculator;
    private MainMenuView view;
    private Player player;
    private ScreenChanger screenChanger;

    public CardboardMenuScreen(Player player, ScreenChanger screenChanger) {
        this.player = player;
        this.screenChanger = screenChanger;
        this.sound = new Sound();
        this.collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {
        if (this.mainMenu.getStartButton().isCollided()) {
            startButtonHit();
        } else if (this.mainMenu.getExitButton().isCollided()) {
            exitButtonHit();
        } else {
            this.mainMenu.update((int) (Gdx.graphics.getDeltaTime() * 1000));
            this.collisionCalculator.run();
            this.mainMenu.setAimDirection(getLookDirection(paramHeadTransform));
        }
    }

    @Override
    public void onDrawEye(Eye paramEye) {
        // Apply the eye transformation to the camera
        Renderer3D.getInstance().onDrawEye(paramEye);
        this.view.render();
        //Renders scene for current eye

    }

    @Override
    public void dispose() {
        CollisionManager.getInstance().getCollidables().clear();
        CollisionManager.getInstance().getObservers().clear();
        this.collisionCalculator.clear();
        this.sound.clearAllActiveMusic();
    }

    private void startButtonHit() {
        this.dispose();
        this.screenChanger.requestScreenChange(GAME, this.player);
    }

    private void exitButtonHit() {
        Gdx.app.exit();
    }

    public void newMainMenu() {
        this.mainMenu = new MainMenu();
        this.view = new MainMenuView(this.mainMenu, true);
        this.controller = new CardboardMenuController(this.mainMenu, DeviceManager.getDevice());
    }

    private Vector3f getLookDirection(HeadTransform paramHeadTransForm) {
        final float[] eulerAngles = new float[3];
        paramHeadTransForm.getEulerAngles(eulerAngles, 0);
        final Vector3f returnVector = MathUtilities.eulerToVector(eulerAngles[0],
                                                                  eulerAngles[2],
                                                                  eulerAngles[1]);
        returnVector.normalize();
        return returnVector;
    }
}


