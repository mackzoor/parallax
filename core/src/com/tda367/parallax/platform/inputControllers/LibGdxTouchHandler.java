package com.tda367.parallax.platform.inputControllers;

import com.tda367.parallax.platform.inputControllers.onScreenTouchpad.IScreenControllerListener;
import com.tda367.parallax.platform.inputControllers.onScreenTouchpad.OnScreenTouchpad;

/**
 * Created by Markus on 2017-04-25.
 */

public class LibGdxTouchHandler implements IScreenControllerListener {

    private InputControlsListener listener;
    private OnScreenTouchpad onScreenTouchpad;

    public LibGdxTouchHandler() {
        onScreenTouchpad = new OnScreenTouchpad();
        onScreenTouchpad.setListener(this);
    }

    public void setListener(InputControlsListener listener) {
        this.listener = listener;
    }

    public void drawTouchPad(){
        onScreenTouchpad.drawTouchPad();
    }

    @Override
    public void onUpdate(float x, float y) {
        listener.xAxisJoystickMovement(x);
        listener.yAxisJoystickMovement(y);
    }
}
