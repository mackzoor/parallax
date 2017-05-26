package com.tda367.parallax.controller.inputhandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import lombok.Setter;

/**
 * Class that checks inputs for a touch device. It checks for touch inputs on the screen,
 * compares button presses with a buttonCode and notifies its observer, an
 * {@link InputControlsListener}, about the user action.
 */

public final class TouchHandler extends InputAdapter {

    @Setter
    private InputControlsListener listener;

    public TouchHandler() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (this.listener != null && (keycode == Input.Keys.BACK
                || keycode == Input.Keys.MENU)) {
            this.listener.pauseButtonPressed();
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.listener.onScreenClick(screenX, Gdx.graphics.getHeight() - screenY);
        return false;
    }
}
