package com.tda367.parallax.controller.controllerclasses.game;

import com.tda367.parallax.controller.inputhandlers.InputControlsAdapter;
import com.tda367.parallax.model.core.Parallax;

/**
 * Created by Markus on 2017-05-22.
 */

class AgelionController extends InputControlsAdapter {

    private float yValue = 0;
    private float xValue = 0;

    private Parallax parallax;

    AgelionController(Parallax parallax) {
        this.parallax = parallax;
    }

    @Override
    public void actionButtonPressed() {
        parallax.getPlayer().getSpaceCraft().action();
    }

    @Override
    public void upButtonDown() {
        yValue += 1;
        updateControls();
    }

    @Override
    public void upButtonUp() {
        yValue -= 1;
        updateControls();
    }

    @Override
    public void rightButtonDown() {
        xValue += 1;
        updateControls();
    }

    @Override
    public void rightButtonUp() {
        xValue -= 1;
        updateControls();
    }

    @Override
    public void downButtonDown() {
        yValue -= 1;
        updateControls();
    }

    @Override
    public void downButtonUp() {
        yValue += 1;
        updateControls();
    }

    @Override
    public void leftButtonDown() {
        xValue -= 1;
        updateControls();
    }

    @Override
    public void leftButtonUp() {
        xValue += 1;
        updateControls();
    }

    @Override
    public void xAxisJoystickMovement(float xValue) {
        this.xValue = xValue;
        updateControls();
    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        this.yValue = yValue;
        updateControls();
    }

    private synchronized void updateControls(){
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(xValue, yValue);
    }
}
