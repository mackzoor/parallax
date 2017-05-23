package com.tda367.parallax.controller.controllerclasses.game;

import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;

/**
 * Controller class used for controlling {@link ISpaceCraft}.
 */

class SpaceCraftController {

    private float yValue = 0;
    private float xValue = 0;

    private ISpaceCraft spaceCraft;

    SpaceCraftController(ISpaceCraft spaceCraft) {
        this.spaceCraft = spaceCraft;
    }

    void actionButtonPressed() {
        spaceCraft.action();
    }

    void upButtonDown() {
        yValue += 1;
        updateControls();
    }

    void upButtonUp() {
        yValue -= 1;
        updateControls();
    }

    void rightButtonDown() {
        xValue += 1;
        updateControls();
    }

    void rightButtonUp() {
        xValue -= 1;
        updateControls();
    }

    void downButtonDown() {
        yValue -= 1;
        updateControls();
    }

    void downButtonUp() {
        yValue += 1;
        updateControls();
    }

    void leftButtonDown() {
        xValue -= 1;
        updateControls();
    }

    void leftButtonUp() {
        xValue += 1;
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

    private synchronized void updateControls(){
        spaceCraft.setDesiredPanVelocity(xValue, yValue);
    }
}