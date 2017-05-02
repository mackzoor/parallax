package com.tda367.parallax.platform.inputControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.tda367.parallax.platform.TouchPadView;

/**
 * Created by Markus on 2017-04-25.
 */

public class LibGdxTouchHandler implements EventListener {

    private TouchPadView view;
    private InputControlsListener listener;

    public LibGdxTouchHandler(TouchPadView view) {
        this.view = view;
        view.addListener(this);
    }

    public void setListener(InputControlsListener listener) {
        this.listener = listener;
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
