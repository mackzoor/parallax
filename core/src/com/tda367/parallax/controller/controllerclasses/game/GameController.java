package com.tda367.parallax.controller.controllerclasses.game;

import com.tda367.parallax.controller.inputhandlers.InputControlsAdapter;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.controller.devicestates.Device;

public class GameController extends InputControlsAdapter {

    private AgelionController agelionController;

    private Parallax parallax;

    public GameController(Parallax parallax, Device device) {
        device.addInputDevices(this);
        this.parallax = parallax;
        agelionController = new AgelionController(parallax);
    }

    @Override
    public void actionButtonPressed() {
        agelionController.actionButtonPressed();
    }

    @Override
    public void secondaryActionButtonPressed() {

    }

    @Override
    public void pauseButtonPressed() {
        parallax.setPaused(!parallax.isPaused());
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
    public void onScreenClick(int xValue, int yValue) {

    }
}
