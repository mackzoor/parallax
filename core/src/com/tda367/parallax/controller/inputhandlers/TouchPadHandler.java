package com.tda367.parallax.controller.inputhandlers;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.tda367.parallax.view.parallaxview.TouchPadView;
import lombok.Setter;

/**
 * Class that handles the on screen touch pad on Android devices. Tells its observer, an
 * {@link InputControlsListener}, if the button is pressed or the joystick is moved.
 */

public final class TouchPadHandler implements EventListener {

    private final TouchPadView view;
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
