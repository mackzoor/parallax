package com.tda367.parallax.controller.controllerclasses.game;

import com.tda367.parallax.controller.inputhandlers.ControllerMode;
import com.tda367.parallax.controller.inputhandlers.InputControlsAdapter;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.view.parallaxview.ParallaxView;

/**
 * Controller class used for user input during game play.
 */

public class GameController extends InputControlsAdapter {

    private AgelionController agelionController;

    private Parallax parallax;
    private ParallaxView parallaxView;

    public GameController(Parallax parallax, ParallaxView parallaxView, Device device) {
        device.addInputDevices(this);
        this.parallax = parallax;
        this.parallaxView = parallaxView;
        agelionController = new AgelionController(parallax.getPlayer().getSpaceCraft());
    }

    @Override
    public void actionButtonPressed() {
        agelionController.actionButtonPressed();
    }

    @Override
    public void secondaryActionButtonPressed() {
        parallaxView.setHudViewActive(!parallaxView.isHudViewActive());
    }

    @Override
    public void pauseButtonPressed() {
        parallax.setPaused(!parallax.isPaused());
        //TODO Add a "pause screen" to parallaxView
    }

    @Override
    public void upButtonDown() {
        agelionController.upButtonDown();
    }

    @Override
    public void upButtonUp() {
        agelionController.upButtonUp();
    }

    @Override
    public void rightButtonDown() {
        agelionController.rightButtonDown();
    }

    @Override
    public void rightButtonUp() {
        agelionController.rightButtonUp();
    }

    @Override
    public void downButtonDown() {
        agelionController.downButtonDown();
    }

    @Override
    public void downButtonUp() {
        agelionController.downButtonUp();
    }

    @Override
    public void leftButtonDown() {
        agelionController.leftButtonDown();
    }

    @Override
    public void leftButtonUp() {
        agelionController.leftButtonUp();
    }

    @Override
    public void xAxisJoystickMovement(float xValue) {
        agelionController.xAxisJoystickMovement(xValue);
    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        agelionController.yAxisJoystickMovement(yValue);
    }

    @Override
    public ControllerMode getControllerMode() {
        return ControllerMode.GAMEMODE;
    }
}
