package com.tda367.parallax.controller.devicestates;


import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;
import com.tda367.parallax.controller.inputcontrollers.LibGdxGamePadHandler;

/**
 * Created by Markus on 2017-04-25.
 */

class CardboardDevice implements Device {

    private LibGdxGamePadHandler gamePadHandler;

    CardboardDevice() {
        this.gamePadHandler = new LibGdxGamePadHandler();
    }

    @Override
    public void addInputDevices(InputControlsListener listener) {
        gamePadHandler.setListener(listener);
    }

    @Override
    public void update() {

    }
}
