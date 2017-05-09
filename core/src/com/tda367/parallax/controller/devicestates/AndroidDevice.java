package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;
import com.tda367.parallax.controller.inputcontrollers.LibGdxGamePadHandler;
import com.tda367.parallax.controller.inputcontrollers.LibGdxTouchHandler;
import com.tda367.parallax.controller.inputcontrollers.LibGdxTouchPadHandler;
import com.tda367.parallax.view.TouchPadView;

class AndroidDevice implements Device {

    private LibGdxTouchPadHandler touchPadHandler;
    private LibGdxTouchHandler touchHandler;
    private TouchPadView touchPadView;
    private LibGdxGamePadHandler gamePadHandler;

    AndroidDevice() {
        this.touchHandler = new LibGdxTouchHandler();
        this.touchPadView = new TouchPadView();
        this.touchPadHandler = new LibGdxTouchPadHandler(touchPadView);
        this.gamePadHandler = new LibGdxGamePadHandler();
    }

    @Override
    public void addInputDevices(InputControlsListener listener) {
        touchHandler.setListener(listener);
        touchPadHandler.setListener(listener);
        gamePadHandler.setListener(listener);
    }

    @Override
    public void update() {
        touchPadView.drawTouchPad();
    }
}
