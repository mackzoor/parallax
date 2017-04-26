package com.tda367.parallax.platform.inputControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by Markus on 2017-04-25.
 */

public class LibGdxTouchHandler implements EventListener {

    private Touchpad.TouchpadStyle touchpadStyle;
    private Touchpad touchpad;
    private Drawable touchBackground;
    private Drawable touchKnob;
    private Skin touchpadSkin;
    private Stage stage;

    private InputControlsListener listener;

    public LibGdxTouchHandler() {
        touchpadSkin = new Skin();
        touchpadSkin.add("background", new Texture(
                "touchpad/background.png"));
        touchpadSkin.add("knob", new Texture("touchpad/knob.png"));

        touchpadStyle = new Touchpad.TouchpadStyle();

        touchBackground = touchpadSkin.getDrawable("background");

        touchKnob = touchpadSkin.getDrawable("knob");
        touchKnob.setMinWidth(200);
        touchKnob.setMinHeight(200);

        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        touchpad = new Touchpad(10, touchpadStyle);

        touchpad.setBounds(45, 45, 400, 400);
        touchpad.addListener(this);

        stage = new Stage();
        stage.addActor(touchpad);
        Gdx.input.setInputProcessor(stage);
    }

    public void setListener(InputControlsListener listener) {
        this.listener = listener;
    }

    public void drawTouchPad(){
        stage.draw();
    }

    @Override
    public boolean handle(Event event) {
        if(listener != null){
            if(event.getListenerActor() == touchpad) {
                listener.xAxisJoystickMovement(touchpad.getKnobPercentX());
                listener.yAxisJoystickMovement(touchpad.getKnobPercentY());
            }
        }
        return false;
    }
}
