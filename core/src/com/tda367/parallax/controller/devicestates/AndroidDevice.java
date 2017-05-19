package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.controller.gamecontrollers.GameController;
import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;
import com.tda367.parallax.controller.inputcontrollers.LibGdxGamePadHandler;
import com.tda367.parallax.controller.inputcontrollers.LibGdxTouchHandler;
import com.tda367.parallax.controller.inputcontrollers.LibGdxTouchPadHandler;
import com.tda367.parallax.view.TouchPadView;

class AndroidDevice implements Device {

    //TODO Should we have gamepad support in android

    private LibGdxTouchPadHandler touchPadHandler;
    private LibGdxTouchHandler touchHandler;
    private TouchPadView touchPadView;
    private LibGdxGamePadHandler gamePadHandler;

    AndroidDevice() {
        this.gamePadHandler = new LibGdxGamePadHandler();
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
        this.touchPadHandler = new LibGdxTouchPadHandler(touchPadView);
        this.gamePadHandler = new LibGdxGamePadHandler();
        touchPadHandler.setListener(listener);
        gamePadHandler.setListener(listener);
    }

    public void initiateTouchController(InputControlsListener listener){
        this.touchHandler = new LibGdxTouchHandler();
        touchHandler.setListener(listener);
        gamePadHandler.setListener(listener);
    }
}
