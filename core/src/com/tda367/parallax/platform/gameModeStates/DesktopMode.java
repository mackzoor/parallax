package com.tda367.parallax.platform.gameModeStates;

import com.tda367.parallax.platform.inputControllers.InputControlsListener;
import com.tda367.parallax.platform.inputControllers.LibGdxKeyboardHandler;
import com.tda367.parallax.platform.inputControllers.LibGdxGamePadHandler;

/**
 * Created by Markus on 2017-04-25.
 */

class DesktopMode implements GameModeState {

    private LibGdxGamePadHandler gamePadHandler;
    private LibGdxKeyboardHandler keyboardHandler;

    DesktopMode() {
        this.gamePadHandler = new LibGdxGamePadHandler();
        this.keyboardHandler = new LibGdxKeyboardHandler();
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
