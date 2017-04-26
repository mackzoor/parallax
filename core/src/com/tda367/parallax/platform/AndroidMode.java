package com.tda367.parallax.platform;

import com.tda367.parallax.platform.inputControllers.InputControlsListener;
import com.tda367.parallax.platform.inputControllers.LibGdxTouchHandler;
import com.tda367.parallax.platform.inputControllers.gamePadController.LibGdxGamePadHandler;

/**
 * Created by Markus on 2017-04-25.
 */

public class AndroidMode implements GameModeState {

    LibGdxTouchHandler touchHandler;
    LibGdxGamePadHandler gamePadHandler;

    public AndroidMode() {
        this.touchHandler = new LibGdxTouchHandler();
        this.gamePadHandler = new LibGdxGamePadHandler();
    }

    @Override
    public void addInputDevices(InputControlsListener listener) {
        touchHandler.setListener(listener);
        gamePadHandler.setListener(listener);
    }

    @Override
    public void update() {
        touchHandler.drawTouchPad();
    }
}
