package com.tda367.parallax.platform.inputControllers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.tda367.parallax.platform.inputControllers.onScreenTouchpad.IScreenControllerListener;
import com.tda367.parallax.platform.inputControllers.onScreenTouchpad.OnScreenTouchpad;

/**
 * Created by Markus on 2017-04-25.
 */

public class LibGdxTouchHandler implements IScreenControllerListener {

    private LibGdxGameController gameController;
    private OnScreenTouchpad onScreenTouchpad;

    public LibGdxTouchHandler() {
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            onScreenTouchpad = new OnScreenTouchpad();
            onScreenTouchpad.setListener(this);
        }
    }

    public void setListener(LibGdxGameController gameController) {
        this.gameController = gameController;
    }

    public void drawTouchpad(){
        onScreenTouchpad.drawTouchpad();
    }

    @Override
    public void onUpdate(float x, float y) {
        gameController.xAxisJoystickMovement(x);
        gameController.yAxisJoystickMovement(y);
    }
}
