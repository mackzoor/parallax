package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;
import com.tda367.parallax.controller.inputcontrollers.LibGdxGamePadHandler;
import com.tda367.parallax.controller.inputcontrollers.LibGdxTouchHandler;
import com.tda367.parallax.platform.GameStateManager;
import com.tda367.parallax.view.TouchPadView;

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
