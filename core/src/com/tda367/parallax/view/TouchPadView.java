package com.tda367.parallax.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import lombok.Getter;

public class TouchPadView {

    @Getter private Touchpad touchpad;
    @Getter private ImageButton actionButton;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Drawable touchBackground;
    private Drawable touchKnob;
    private Drawable actionButtonBackground;
    private Skin touchpadSkin;
    private Stage stage;

    public TouchPadView() {
        touchpadSkin = new Skin();
        touchpadSkin.add("background", new Texture("touchpad/background.png"));
        touchpadSkin.add("knob", new Texture("touchpad/knob.png"));
        touchpadSkin.add("actionButton", new Texture("touchpad/actionButton.png"));

        touchpadStyle = new Touchpad.TouchpadStyle();

        touchBackground = touchpadSkin.getDrawable("background");
        actionButtonBackground = touchpadSkin.getDrawable("actionButton");
        touchKnob = touchpadSkin.getDrawable("knob");

        touchKnob.setMinWidth(200);
        touchKnob.setMinHeight(200);

        actionButtonBackground.setMinWidth(200);
        actionButtonBackground.setMinHeight(200);

        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        touchpad = new Touchpad(10, touchpadStyle);

        actionButton = new ImageButton(actionButtonBackground);
        actionButton.setBounds(1400, 170, 200, 200);

        touchpad.setBounds(45, 45, 400, 400);

        stage = new Stage();
        stage.addActor(touchpad);
        stage.addActor(actionButton);
        Gdx.input.setInputProcessor(stage);
    }

    public void addListener(EventListener listener) {
        touchpad.addListener(listener);
        actionButton.addListener(listener);
    }

    public void drawTouchPad(){
        stage.draw();
    }
}
