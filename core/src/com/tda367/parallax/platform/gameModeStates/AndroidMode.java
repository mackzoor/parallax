package com.tda367.parallax.platform.gameModeStates;

import com.tda367.parallax.platform.TouchPadView;
import com.tda367.parallax.platform.inputControllers.InputControlsListener;
import com.tda367.parallax.platform.inputControllers.LibGdxTouchHandler;
import com.tda367.parallax.platform.inputControllers.LibGdxGamePadHandler;

/**
 * Created by Markus on 2017-04-25.
 */

class AndroidMode implements GameModeState {

    private LibGdxTouchHandler touchHandler;
    private TouchPadView touchPadView;
    private LibGdxGamePadHandler gamePadHandler;

    AndroidMode() {
        this.touchPadView = new TouchPadView();
        this.touchHandler = new LibGdxTouchHandler(touchPadView);
        this.gamePadHandler = new LibGdxGamePadHandler();
    }

    @Override
    public void addInputDevices(InputControlsListener listener) {
        touchHandler.setListener(listener);
        gamePadHandler.setListener(listener);
    }

    @Override
    public void update() {
        touchPadView.drawTouchPad();
    }
}
