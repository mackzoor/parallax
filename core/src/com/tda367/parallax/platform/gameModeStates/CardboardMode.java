package com.tda367.parallax.platform.gameModeStates;

import com.tda367.parallax.platform.inputControllers.InputControlsListener;
import com.tda367.parallax.platform.inputControllers.LibGdxGamePadHandler;

/**
 * Created by Markus on 2017-04-25.
 */

class CardboardMode implements GameModeState {

    private LibGdxGamePadHandler gamePadHandler;

    CardboardMode() {
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