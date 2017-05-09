package com.tda367.parallax.controller.inputcontrollers;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.tda367.parallax.view.TouchPadView;
import lombok.Setter;

public final class LibGdxTouchPadHandler implements EventListener {

    private TouchPadView view;
    @Setter private InputControlsListener listener;

    public LibGdxTouchPadHandler(TouchPadView view) {
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
}
