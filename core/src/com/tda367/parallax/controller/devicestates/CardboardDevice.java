package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.controller.inputhandlers.InputControlsListener;
import com.tda367.parallax.controller.inputhandlers.GamePadHandler;

class CardboardDevice implements Device {

    private GamePadHandler gamePadHandler;

    CardboardDevice() {
        this.gamePadHandler = new GamePadHandler();
    }

    @Override
    public void addInputDevices(InputControlsListener listener) {
        gamePadHandler.setListener(listener);
    }

    @Override
    public void update() {
    }
}
