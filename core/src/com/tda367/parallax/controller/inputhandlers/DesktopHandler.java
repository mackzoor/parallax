package com.tda367.parallax.controller.inputhandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Class that checks inputs for a Desktop computer. It checks for mouse/touch inputs on the screen,
 * compares key presses with a keyCode and notifies its observer, an
 * {@link InputControlsListener}, about the user action.
 */

public final class DesktopHandler extends InputAdapter {

    private InputControlsListener listener;

    public DesktopHandler() {
        Gdx.input.setInputProcessor(this);
    }

    public void setListener(InputControlsListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (listener != null) {
            if (keycode == Input.Keys.SPACE || keycode == Input.Keys.Z) {
                listener.actionButtonPressed();
            } else if (keycode == Input.Keys.X) {
                listener.secondaryActionButtonPressed();
            } else if (keycode == Input.Keys.ESCAPE) {
                listener.pauseButtonPressed();
            }
            checkDirectionbuttons(keycode);
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            listener.leftButtonUp();
        } else if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            listener.upButtonUp();
        } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            listener.rightButtonUp();
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            listener.downButtonUp();
        }

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        listener.onScreenClick(screenX, Gdx.graphics.getHeight() - screenY);
        return false;
    }

    private void checkDirectionbuttons(int keycode) {

        if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            listener.leftButtonDown();
        } else if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            listener.upButtonDown();
        } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            listener.rightButtonDown();
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            listener.downButtonDown();
        }

    }
}
