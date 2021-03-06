package com.tda367.parallax.controller.screens;

import com.badlogic.gdx.Gdx;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.controllerclasses.CardboardMenuController;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.screens.cardboardadapter.CardboardScreenAdapter;
import com.tda367.parallax.model.externalcollsioncalculators.CollisionCalculator;
import com.tda367.parallax.model.core.Player;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.menu.MainMenu;
import com.tda367.parallax.utilities.MathUtilities;
import com.tda367.parallax.view.parallaxview.MainMenuView;
import com.tda367.parallax.view.rendering.Renderer3D;

import javax.vecmath.Vector3f;

import static com.tda367.parallax.controller.screens.ScreenState.GAME;


public class CardboardMenuScreen extends CardboardScreenAdapter {
    private static final int SEC_TO_MILLISEC = 1000;
    private CardboardMenuController controller;
    private MainMenu mainMenu;
    private final CollisionCalculator collisionCalculator;
    private MainMenuView view;
    private final Player player;
    private final ScreenChanger screenChanger;

    public CardboardMenuScreen(Player player, ScreenChanger screenChanger) {
        super();
        this.player = player;
        this.screenChanger = screenChanger;
        this.collisionCalculator = new CollisionCalculator();
    }

    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {
        if (this.mainMenu.getStartButton().isCollided()) {
            startButtonHit();
        } else if (this.mainMenu.getExitButton().isCollided()) {
            exitButtonHit();
        } else {
            this.mainMenu.update((int) (Gdx.graphics.getDeltaTime() * SEC_TO_MILLISEC));
            this.collisionCalculator.run();
            this.mainMenu.setAimDirection(getLookDirection(paramHeadTransform));
        }
    }

    @Override
    public void onDrawEye(Eye paramEye) {
        Renderer3D.getInstance().onDrawEye(paramEye);
        this.view.render();
    }

    @Override
    public void dispose() {
        CollisionManager.getInstance().getCollidables().clear();
        CollisionManager.getInstance().getObservers().clear();
        this.collisionCalculator.clear();
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
        this.view = new MainMenuView(this.mainMenu, true, false);
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


