package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.controller.inputhandlers.DesktopHandler;
import com.tda367.parallax.controller.inputhandlers.GamePadHandler;
import com.tda367.parallax.controller.inputhandlers.InputControlsListener;

/**
 * Manages the input-handlers for Desktop computers.
 */

class DesktopDevice implements Device {

    private final GamePadHandler gamePadHandler;
    private final DesktopHandler keyboardHandler;

    DesktopDevice() {
        this.gamePadHandler = new GamePadHandler();
        this.keyboardHandler = new DesktopHandler();
    }

    @Override
    public void addInputDevices(InputControlsListener listener) {
        this.gamePadHandler.setListener(listener);
        this.keyboardHandler.setListener(listener);
    }

    @Override
    public void update() {
        //Not applicable
    }
}
