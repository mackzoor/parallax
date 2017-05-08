package com.tda367.parallax.platform.gamemodestates;

import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;
import com.tda367.parallax.controller.inputcontrollers.LibGdxDesktopHandler;
import com.tda367.parallax.controller.inputcontrollers.LibGdxGamePadHandler;

/**
 * Created by Markus on 2017-04-25.
 */

class DesktopMode implements GameModeState {

    private LibGdxGamePadHandler gamePadHandler;
    private LibGdxDesktopHandler keyboardHandler;

    DesktopMode() {
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
