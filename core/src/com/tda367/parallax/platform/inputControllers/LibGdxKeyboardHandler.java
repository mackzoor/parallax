package com.tda367.parallax.platform.inputControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Markus on 2017-04-25.
 */

public class LibGdxKeyboardHandler implements InputProcessor {

    private LibGdxGameController gameController;

    public LibGdxKeyboardHandler(LibGdxGameController gameController) {
        Gdx.input.setInputProcessor(this);
        this.gameController = gameController;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.SPACE || keycode == Input.Keys.Z) {
            gameController.actionButtonPressed();
        } else if (keycode == Input.Keys.X) {
            gameController.secondaryActionButtonPressed();
        } else if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            gameController.leftButtonPressed();
        } else if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            gameController.upButtonPressed();
        } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            gameController.rightButtonPressed();
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            gameController.downButtonPressed();
        } else if (keycode == Input.Keys.ESCAPE) {
            gameController.pauseButtonPressed();
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            gameController.leftButtonUp();
        } else if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            gameController.upButtonUp();
        } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            gameController.rightButtonUp();
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            gameController.downButtonUp();
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
