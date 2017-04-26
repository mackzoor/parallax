package com.tda367.parallax.platform.inputControllers.onScreenTouchpad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public class OnScreenTouchpad implements EventListener {

    private Touchpad.TouchpadStyle touchpadStyle;
    private Touchpad touchpad;
    private Drawable touchBackground;
    private Drawable touchKnob;
    private IScreenControllerListener listener;
    private Skin touchpadSkin;
    private Stage stage;

    public OnScreenTouchpad() {

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
    public void setListener(IScreenControllerListener listener) {
        this.listener = listener;
    }

    public void drawTouchPad(){
        stage.draw();
    }

    public void alertListener(){
        if(listener != null){
            listener.onUpdate(touchpad.getKnobPercentX(),touchpad.getKnobPercentY());
        }
    }

    @Override
    public boolean handle(Event event) {
        alertListener();
        return false;
    }
}