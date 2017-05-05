package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.controller.inputControllers.InputControlsListener;
import com.tda367.parallax.controller.inputControllers.LibGdxDesktopHandler;
import com.tda367.parallax.controller.inputControllers.LibGdxGamePadHandler;

/**
 * Created by Markus on 2017-04-25.
 */

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
