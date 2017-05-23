package com.tda367.parallax.controller.devicestates;


import com.tda367.parallax.controller.inputhandlers.InputControlsListener;
import com.tda367.parallax.controller.inputhandlers.LibGdxDesktopHandler;
import com.tda367.parallax.controller.inputhandlers.LibGdxGamePadHandler;

class DesktopDevice implements Device {

    private LibGdxGamePadHandler gamePadHandler;
    private LibGdxDesktopHandler keyboardHandler;

    DesktopDevice() {
        this.gamePadHandler = new LibGdxGamePadHandler();
        this.keyboardHandler = new LibGdxDesktopHandler();
    }

    @Override
    public void addInputDevices(InputControlsListener listener) {
        gamePadHandler.setListener(listener);
        keyboardHandler.setListener(listener);
    }

    @Override
    public void update() {

    }
}
