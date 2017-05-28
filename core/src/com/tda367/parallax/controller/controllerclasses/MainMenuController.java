package com.tda367.parallax.controller.controllerclasses;

import com.badlogic.gdx.Gdx;
import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.controller.inputhandlers.ControllerMode;
import com.tda367.parallax.controller.inputhandlers.InputControlsAdapter;
import com.tda367.parallax.model.menu.MainMenu;

import javax.vecmath.Vector3f;

/**
 * Controller class used for user input in the menu for Desktop and Android.
 */

public class MainMenuController extends InputControlsAdapter {

    private final MainMenu mainMenu;
    private static final double AIM_MODIFIER = 3.8;

    public MainMenuController(MainMenu mainMenu, Device device) {
        super();
        this.mainMenu = mainMenu;
        device.addInputDevices(this);
    }

    @Override
    public void actionButtonPressed() {

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
        final float aimXValue = xValue - Gdx.graphics.getWidth()/2;
        final float aimYValue = yValue - Gdx.graphics.getHeight()/2;

        this.mainMenu.setAimDirection(new Vector3f(aimXValue, (float)(Gdx.graphics.getWidth()/AIM_MODIFIER),aimYValue));
        this.mainMenu.action();
    }

    @Override
    public ControllerMode getControllerMode() {
        return ControllerMode.MENUMODE;
    }
}
