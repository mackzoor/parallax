package com.tda367.parallax.platform;

import com.tda367.parallax.parallaxCore.Parallax;
import com.tda367.parallax.platform.inputControllers.InputControlsListener;

import javax.vecmath.Vector2f;

/**
 * Created by Markus on 2017-04-11.
 */
class ParallaxLibGDXController implements InputControlsListener {

    private float yValue = 0;
    private float xValue = 0;

    private Parallax parallax;
    private float panSpeed;

    ParallaxLibGDXController(Parallax parallax, com.tda367.parallax.platform.gameModeStates.GameModeState gameModeState) {
        gameModeState.addInputDevices(this);
        this.parallax = parallax;
        this.panSpeed = 1;
    }

    @Override
    public void actionButtonPressed() {
        parallax.getPlayer().getSpaceCraft().action();
    }

    @Override
    public void secondaryActionButtonPressed() {

    }

    @Override
    public void pauseButtonPressed() {

    }

    @Override
    public void upButtonPressed() {
        yValue += 1;
        updateControls();
    }

    @Override
    public void upButtonUp() {
        yValue -= 1;
        updateControls();
    }

    @Override
    public void rightButtonPressed() {
        xValue += 1;
        updateControls();
    }

    @Override
    public void rightButtonUp() {
        xValue -= 1;
        updateControls();
    }

    @Override
    public void downButtonPressed() {
        yValue = -1;
        updateControls();
    }

    @Override
    public void downButtonUp() {
        yValue += 1;
        updateControls();
    }

    @Override
    public void leftButtonPressed() {
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
        if (Math.abs(xValue) > 0.15){
            this.xValue = xValue;
        } else {
            this.xValue = 0;
        }
        updateControls();
    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        if (Math.abs(yValue) > 0.15){
            this.yValue = yValue;
        } else {
            this.yValue = 0;
        }
        updateControls();
    }

    private synchronized void updateControls(){
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(xValue, yValue);
    }
}
