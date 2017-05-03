package com.tda367.parallax.platform.inputControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Markus on 2017-04-25.
 */

public class LibGdxDesktopHandler implements InputProcessor {

    private InputControlsListener listener;

    public LibGdxDesktopHandler() {
        Gdx.input.setInputProcessor(this);
    }

    public void setListener(InputControlsListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE || keycode == Input.Keys.Z) {
            listener.actionButtonPressed();
        } else if (keycode == Input.Keys.X) {
            listener.secondaryActionButtonPressed();
        } else if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            listener.leftButtonPressed();
        } else if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            listener.upButtonPressed();
        } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            listener.rightButtonPressed();
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            listener.downButtonPressed();
        } else if (keycode == Input.Keys.ESCAPE) {
            listener.pauseButtonPressed();
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
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        listener.onScreenClick(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
