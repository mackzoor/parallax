package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.platform.GameStateManager;
import com.tda367.parallax.view.TouchPadView;
import com.tda367.parallax.controller.inputControllers.InputControlsListener;
import com.tda367.parallax.controller.inputControllers.LibGdxTouchHandler;
import com.tda367.parallax.controller.inputControllers.LibGdxGamePadHandler;

/**
 * Created by Markus on 2017-04-25.
 */

class AndroidDevice implements Device {

    private LibGdxTouchHandler touchHandler;
    private TouchPadView touchPadView;
    private LibGdxGamePadHandler gamePadHandler;

    AndroidDevice() {
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
        if(GameStateManager.getInstance().getState() == GameStateManager.State.PLAY)
            touchPadView.drawTouchPad();
    }
}
