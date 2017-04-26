package com.tda367.parallax.platform;

import com.tda367.parallax.platform.inputControllers.InputControlsListener;
import com.tda367.parallax.platform.inputControllers.gamePadController.LibGdxGamePadHandler;

/**
 * Created by Markus on 2017-04-25.
 */

public class CardboardMode implements GameModeState {

    LibGdxGamePadHandler gamePadHandler;

    public CardboardMode() {
        this.gamePadHandler = new LibGdxGamePadHandler();
    }

    @Override
    public void addInputDevices(InputControlsListener controller) {
        gamePadHandler.setListener(controller);
    }
}
