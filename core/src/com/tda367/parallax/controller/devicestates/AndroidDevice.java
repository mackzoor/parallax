package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.controller.controllerclasses.game.GameController;
import com.tda367.parallax.controller.inputhandlers.InputControlsListener;
import com.tda367.parallax.controller.inputhandlers.GamePadHandler;
import com.tda367.parallax.controller.inputhandlers.TouchHandler;
import com.tda367.parallax.controller.inputhandlers.TouchPadHandler;
import com.tda367.parallax.view.TouchPadView;

class AndroidDevice implements Device {

    //TODO Should we have gamepad support in android

    private TouchPadHandler touchPadHandler;
    private TouchHandler touchHandler;
    private TouchPadView touchPadView;
    private GamePadHandler gamePadHandler;

    AndroidDevice() {
        this.gamePadHandler = new GamePadHandler();
    }

    @Override
    public void addInputDevices(InputControlsListener listener) {
        if(listener instanceof GameController) {
            initiateGameController(listener);
        }else{
            initiateTouchController(listener);
        }
    }

    @Override
    public void update() {
        touchPadView.drawTouchPad();
    }

    private void initiateGameController(InputControlsListener listener){
        this.touchPadView = new TouchPadView();
        this.touchPadHandler = new TouchPadHandler(touchPadView);
        this.gamePadHandler = new GamePadHandler();
        touchPadHandler.setListener(listener);
        gamePadHandler.setListener(listener);
    }

    public void initiateTouchController(InputControlsListener listener){
        this.touchHandler = new TouchHandler();
        touchHandler.setListener(listener);
        gamePadHandler.setListener(listener);
    }
}
