package com.tda367.parallax.controller.controllerclasses.game;

import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;

/**
 * Controller class used for controlling {@link ISpaceCraft}.
 */

class SpaceCraftController {

    private float yValue;
    private float xValue;

    private final ISpaceCraft spaceCraft;

    SpaceCraftController(ISpaceCraft spaceCraft) {
        this.yValue = 0;
        this.xValue = 0;
        this.spaceCraft = spaceCraft;
    }

    void actionButtonPressed() {
        this.spaceCraft.action();
    }

    void upButtonDown() {
        this.yValue += 1;
        updateControls();
    }

    void upButtonUp() {
        this.yValue -= 1;
        updateControls();
    }

    void rightButtonDown() {
        this.xValue += 1;
        updateControls();
    }

    void rightButtonUp() {
        this.xValue -= 1;
        updateControls();
    }

    void downButtonDown() {
        this.yValue -= 1;
        updateControls();
    }

    void downButtonUp() {
        this.yValue += 1;
        updateControls();
    }

    void leftButtonDown() {
        this.xValue -= 1;
        updateControls();
    }

    void leftButtonUp() {
        this.xValue += 1;
        updateControls();
    }

    void xAxisJoystickMovement(float xValue) {
        this.xValue = xValue;
        updateControls();
    }

    void yAxisJoystickMovement(float yValue) {
        this.yValue = yValue;
        updateControls();
    }

    private synchronized void updateControls() {
        this.spaceCraft.setDesiredPanVelocity(this.xValue, this.yValue);
    }
}
