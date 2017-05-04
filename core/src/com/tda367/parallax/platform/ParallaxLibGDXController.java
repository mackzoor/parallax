package com.tda367.parallax.platform;

import com.tda367.parallax.parallaxCore.Parallax;
import com.tda367.parallax.platform.gameModeStates.GameModeState;
import com.tda367.parallax.platform.inputControllers.InputControlsListener;

/**
 * Created by Markus on 2017-04-11.
 */
class ParallaxLibGDXController implements InputControlsListener {

    private float yValue = 0;
    private float xValue = 0;

    private Parallax parallax;
    private float panSpeed;

    ParallaxLibGDXController(Parallax parallax, GameModeState gameModeState) {
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
        yValue = -1;
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

    @Override
    public void onScreenClick(int xValue, int yValue) {

    }

    private synchronized void updateControls(){
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(xValue, yValue);
    }
}
