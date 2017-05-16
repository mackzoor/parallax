package com.tda367.parallax.controller.gamecontrollers;

import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;
import com.tda367.parallax.model.gameover.GameOverModel;

/**
 * Created by Rasmus on 2017-05-16.
 */
public class GameOverController implements InputControlsListener {

    private GameOverModel model;

    public GameOverController(GameOverModel model, Device device){
        this.model = model;
        device.addInputDevices(this);
    }

    @Override
    public void actionButtonPressed() {
        model.exit();
    }

    @Override
    public void secondaryActionButtonPressed() {

    }

    @Override
    public void pauseButtonPressed() {

    }

    @Override
    public void upButtonDown() {

    }

    @Override
    public void upButtonUp() {

    }

    @Override
    public void rightButtonDown() {

    }

    @Override
    public void rightButtonUp() {

    }

    @Override
    public void downButtonDown() {

    }

    @Override
    public void downButtonUp() {

    }

    @Override
    public void leftButtonDown() {

    }

    @Override
    public void leftButtonUp() {

    }

    @Override
    public void xAxisJoystickMovement(float xValue) {

    }

    @Override
    public void yAxisJoystickMovement(float yValue) {

    }

    @Override
    public void onScreenClick(int xValue, int yValue) {

    }
}
