package com.tda367.parallax.controller.gamecontrollers;

import com.tda367.parallax.model.parallaxcore.Parallax;
import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;
import com.tda367.parallax.controller.devicestates.Device;

public class GameController extends ControllerAdapter {

    private float yValue = 0;
    private float xValue = 0;

    private Parallax parallax;

    public GameController(Parallax parallax, Device device) {
        device.addInputDevices(this);
        this.parallax = parallax;
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

    @Override
    public void onScreenClick(int xValue, int yValue) {

    }

    private synchronized void updateControls(){
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(xValue, yValue);
    }
}
