package com.tda367.parallax.controller.inputcontrollers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.tda367.parallax.view.TouchPadView;
import lombok.Setter;

public class LibGdxTouchHandler implements EventListener, InputProcessor {

    private TouchPadView view;
    @Setter private InputControlsListener listener;

    public LibGdxTouchHandler(TouchPadView view) {
        this.view = view;
        view.addListener(this);
    }

    @Override
    public boolean handle(Event event) {
        if(listener != null){
            if(event.getListenerActor() == view.getTouchpad()) {
                listener.xAxisJoystickMovement(view.getTouchpad().getKnobPercentX());
                listener.yAxisJoystickMovement(view.getTouchpad().getKnobPercentY());
            } else if (event.getListenerActor() == view.getActionButton()) {
                listener.actionButtonPressed();
            }
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (listener != null) {
            if (keycode == Input.Keys.BACK || keycode == Input.Keys.MENU) {
                listener.pauseButtonPressed();
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.debug("TOUCHDOWN", "X: " + screenX + " Y: " + screenY);
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
