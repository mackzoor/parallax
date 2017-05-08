package com.tda367.parallax.platform.gamemodestates;

import com.tda367.parallax.platform.GameStateManager;
import com.tda367.parallax.view.TouchPadView;
import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;
import com.tda367.parallax.controller.inputcontrollers.LibGdxTouchHandler;
import com.tda367.parallax.controller.inputcontrollers.LibGdxGamePadHandler;

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
        if(GameStateManager.getInstance().getState() == GameStateManager.State.PLAY)
            touchPadView.drawTouchPad();
    }
}
