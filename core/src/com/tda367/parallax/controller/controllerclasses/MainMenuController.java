package com.tda367.parallax.controller.controllerclasses;

import com.tda367.parallax.controller.inputhandlers.ControllerMode;
import com.tda367.parallax.controller.inputhandlers.InputControlsAdapter;
import com.tda367.parallax.model.menu.buttons.Button;
import com.tda367.parallax.model.menu.buttons.ExitButton;
import com.tda367.parallax.model.menu.buttons.StartButton;
import com.tda367.parallax.model.menu.MainMenuModel;
import com.tda367.parallax.controller.devicestates.Device;

import lombok.Getter;

/**
 * Controller class used for user input in the menu for Desktop and Android.
 */

public class MainMenuController extends InputControlsAdapter {

    private MainMenuModel mainMenuModel;
    @Getter
    private boolean startButtonPressed;
    @Getter
    private boolean exitButtonPressed;

    public MainMenuController(MainMenuModel mainMenuModel, Device device) {
        this.mainMenuModel = mainMenuModel;
        startButtonPressed = false;
        exitButtonPressed = false;
        device.addInputDevices(this);
    }

    @Override
    public void actionButtonPressed() {
        if (mainMenuModel.getStartButton().isMarked()) {
            startButtonPressed = true;
        } else if (mainMenuModel.getExitButton().isMarked()) {
            exitButtonPressed = true;
        }
    }

    @Override
    public void upButtonDown() {
        mainMenuModel.iterateUp();
    }


    @Override
    public void downButtonDown() {
        mainMenuModel.iterateDown();
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
            startButtonPressed = true;
        } else if (pressedButton instanceof ExitButton) {
            exitButtonPressed = true;
        }
    }

    @Override
    public ControllerMode getControllerMode() {
        return ControllerMode.MENUMODE;
    }
}
