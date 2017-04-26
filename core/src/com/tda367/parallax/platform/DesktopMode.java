package com.tda367.parallax.platform;

import com.tda367.parallax.platform.inputControllers.InputControlsListener;
import com.tda367.parallax.platform.inputControllers.LibGdxKeyboardHandler;
import com.tda367.parallax.platform.inputControllers.gamePadController.LibGdxGamePadHandler;

/**
 * Created by Markus on 2017-04-25.
 */

public class DesktopMode implements GameModeState {

    private LibGdxGamePadHandler gamePadHandler;
    private LibGdxKeyboardHandler keyboardHandler;

    public DesktopMode() {
        this.gamePadHandler = new LibGdxGamePadHandler();
        this.keyboardHandler = new LibGdxKeyboardHandler();
    }

    @Override
    public void addInputDevices(InputControlsListener controller) {
        gamePadHandler.setListener(controller);
        keyboardHandler.setListener(controller);
    }
}
