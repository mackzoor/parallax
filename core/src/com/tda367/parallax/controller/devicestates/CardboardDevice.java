package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.controller.inputhandlers.GamePadHandler;
import com.tda367.parallax.controller.inputhandlers.InputControlsListener;

class CardboardDevice implements Device {

    private GamePadHandler gamePadHandler;

    CardboardDevice() {
        this.gamePadHandler = new GamePadHandler();
    }

    @Override
    public void addInputDevices(InputControlsListener listener) {
        this.gamePadHandler.setListener(listener);
    }

    @Override
    public void update() {
    }
}
