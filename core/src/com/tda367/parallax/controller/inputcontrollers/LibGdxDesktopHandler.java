package com.tda367.parallax.controller.inputcontrollers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public final class LibGdxDesktopHandler implements InputProcessor {

    private InputControlsListener listener;

    public LibGdxDesktopHandler() {
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
                return false;
            }

            if (keycode == Input.Keys.X) {
                listener.secondaryActionButtonPressed();
                return false;
            }

            if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
                listener.leftButtonDown();
                return false;
            }

            if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
                listener.upButtonDown();
                return false;
            }

            if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
                listener.rightButtonDown();
                return false;
            }

            if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
                listener.downButtonDown();
                return false;
            }

            if (keycode == Input.Keys.ESCAPE) {
                listener.pauseButtonPressed();
                return false;
            }
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
        listener.onScreenClick(screenX, Gdx.graphics.getHeight() - screenY);
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
