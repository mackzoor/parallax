package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.controller.inputhandlers.GamePadHandler;
import com.tda367.parallax.controller.inputhandlers.InputControlsListener;
import com.tda367.parallax.controller.inputhandlers.TouchHandler;
import com.tda367.parallax.controller.inputhandlers.TouchPadHandler;
import com.tda367.parallax.view.parallaxview.TouchPadView;

import static com.tda367.parallax.controller.inputhandlers.ControllerMode.GAMEMODE;

/**
 * Manages the input-handlers for Android devices.
 */

class AndroidDevice implements Device {

    private TouchPadHandler touchPadHandler;
    private TouchHandler touchHandler;
    private TouchPadView touchPadView;
    private GamePadHandler gamePadHandler;

    AndroidDevice() {
        this.gamePadHandler = new GamePadHandler();
    }

    @Override
    public void addInputDevices(InputControlsListener listener) {
        if (listener.getControllerMode() == GAMEMODE) {
            initiateGameController(listener);
        } else {
            initiateTouchController(listener);
        }
    }

    @Override
    public void update() {
        this.touchPadView.drawTouchPad();
    }

    private void initiateGameController(InputControlsListener listener) {
        this.touchPadView = new TouchPadView();
        this.touchPadHandler = new TouchPadHandler(this.touchPadView);
        this.gamePadHandler = new GamePadHandler();
        this.touchPadHandler.setListener(listener);
        this.gamePadHandler.setListener(listener);
    }

    private void initiateTouchController(InputControlsListener listener) {
        this.touchHandler = new TouchHandler();
        this.touchHandler.setListener(listener);
        this.gamePadHandler.setListener(listener);
    }
}
