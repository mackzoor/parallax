package com.tda367.parallax.platform.inputControllers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.tda367.parallax.platform.inputControllers.onScreenTouchpad.IScreenControllerListener;
import com.tda367.parallax.platform.inputControllers.onScreenTouchpad.OnScreenTouchpad;

/**
 * Created by Markus on 2017-04-25.
 */

public class LibGdxTouchHandler implements IScreenControllerListener {

    private InputControlsListener listener;
    private OnScreenTouchpad onScreenTouchpad;

    public LibGdxTouchHandler() {
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            onScreenTouchpad = new OnScreenTouchpad();
            onScreenTouchpad.setListener(this);
        }
    }

    public void setListener(InputControlsListener listener) {
        this.listener = listener;
    }

    public void drawTouchpad(){
        onScreenTouchpad.drawTouchpad();
    }

    @Override
    public void onUpdate(float x, float y) {
        listener.xAxisJoystickMovement(x);
        listener.yAxisJoystickMovement(y);
    }
}
