package com.tda367.parallax.controller.inputhandlers;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.tda367.parallax.view.TouchPadView;
import lombok.Setter;

/**
 *
 */

public final class TouchPadHandler implements EventListener {

    private TouchPadView view;
    @Setter
    private InputControlsListener listener;

    public TouchPadHandler(TouchPadView view) {
        this.view = view;
        view.addListener(this);
    }

    @Override
    public boolean handle(Event event) {
        if (this.listener != null) {
            if (event.getListenerActor() == this.view.getTouchpad()) {
                this.listener.xAxisJoystickMovement(this.view.getTouchpad().getKnobPercentX());
                this.listener.yAxisJoystickMovement(this.view.getTouchpad().getKnobPercentY());
            } else if (event.getListenerActor() == this.view.getActionButton()) {
                this.listener.actionButtonPressed();
            }
        }
        return false;
    }
}
