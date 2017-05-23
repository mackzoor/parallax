package com.tda367.parallax.controller.controllerclasses;

import com.tda367.parallax.controller.inputhandlers.ControllerMode;
import com.badlogic.gdx.Gdx;
import com.tda367.parallax.controller.inputhandlers.InputControlsAdapter;
import com.tda367.parallax.model.cardboardmenu.MainMenu;
import com.tda367.parallax.model.menu.buttons.Button;
import com.tda367.parallax.model.menu.buttons.ExitButton;
import com.tda367.parallax.model.menu.buttons.StartButton;
import com.tda367.parallax.model.menu.MainMenuModel;
import com.tda367.parallax.controller.devicestates.Device;

import lombok.Getter;

/**
 * Controller class used for user input in the menu for Desktop and Android.
 */

import javax.vecmath.Vector3f;

public class MainMenuController extends InputControlsAdapter {

    private MainMenu mainMenu;

    public MainMenuController(MainMenu mainMenu, Device device) {
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
        if(xValue < Gdx.graphics.getWidth()/2){
            xValue = xValue - Gdx.graphics.getWidth()/2;
        }else {
            xValue = xValue - Gdx.graphics.getWidth()/2;
        }
        if(yValue < Gdx.graphics.getWidth()/2){
            yValue = yValue - Gdx.graphics.getWidth()/2;
        }
        mainMenu.action(new Vector3f(xValue, yValue,-2));
    }

    @Override
    public ControllerMode getControllerMode() {
        return ControllerMode.MENUMODE;
    }
}
