package com.tda367.parallax.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tda367.parallax.controller.gamestates.GameStateManager;
import com.tda367.parallax.model.menu.buttons.Button;
import com.tda367.parallax.model.menu.buttons.ExitButton;
import com.tda367.parallax.model.menu.buttons.StartButton;
import com.tda367.parallax.model.menu.mainmenu.MainMenuModel;
import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;


/**
 * Created by Rasmus on 2017-05-02.
 */
public class MainMenuController implements InputControlsListener {

    private MainMenuModel mainMenuModel;
    private Game game;

    public MainMenuController(MainMenuModel mainMenuModel, Device device, Game game) {
        this.mainMenuModel = mainMenuModel;
        this.game = game;
        device.addInputDevices(this);
    }

    @Override
    public void actionButtonPressed() {
        if (mainMenuModel.getStartButton().isMarked()) {
            GameStateManager.setGameScreen(game);
        } else if (mainMenuModel.getExitButton().isMarked()) {
            Gdx.app.exit();
        }
    }

    @Override
    public void secondaryActionButtonPressed() {

    }

    @Override
    public void pauseButtonPressed() {

    }

    @Override
    public void upButtonDown() {
        mainMenuModel.iterateUp();
    }

    @Override
    public void upButtonUp() {

    }

    @Override
    public void rightButtonDown() {

    }

    @Override
    public void rightButtonUp() {

    }

    @Override
    public void downButtonDown() {
        mainMenuModel.iterateDown();
    }

    @Override
    public void downButtonUp() {

    }

    @Override
    public void leftButtonDown() {

    }

    @Override
    public void leftButtonUp() {

    }

    @Override
    public void xAxisJoystickMovement(float xValue) {

    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        if (yValue > 0) {
            upButtonDown();
        } else if (yValue < 0) {
            downButtonDown();
        }
    }

    @Override
    public void onScreenClick(int xValue, int yValue) {

        Button pressedButton = mainMenuModel.buttonPressed(xValue, yValue);

        if (pressedButton instanceof StartButton) {
            GameStateManager.setGameScreen(game);
        } else if (pressedButton instanceof ExitButton) {
            Gdx.app.exit();
        }
    }
}
