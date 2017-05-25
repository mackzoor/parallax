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

/**
 * Class responsible for drawing the on screen game pad that's visible on Android
 */

public class TouchPadView {

    @Getter private Touchpad touchpad;
    @Getter private ImageButton actionButton;
    private static float BUTTON_DIAMETER_DIVISOR = 9.6f;
    private static float BUTTON_POSITON_DIVISOR = 10f;
    private static float TOUCH_PAD_DIAMETER_DIVISOR = 4.8f;
    private static float TOUCH_PAD_POSITON_DIVISOR = 24f;

    private Stage stage;

    public TouchPadView() {
        Skin gamePadSkin = new Skin();
        gamePadSkin.add("background", new Texture("touchpad/background.png"));
        gamePadSkin.add("knob", new Texture("touchpad/knob.png"));
        gamePadSkin.add("actionButton", new Texture("touchpad/actionButton.png"));

        this.stage = new Stage();

        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        initButton(gamePadSkin, screenWidth, screenHeight);
        initTouchPad(gamePadSkin, screenWidth, screenHeight);

        Gdx.input.setInputProcessor(this.stage);
    }

    public void addListener(EventListener listener) {
        this.touchpad.addListener(listener);
        this.actionButton.addListener(listener);
    }

    public void drawTouchPad(){
        this.stage.draw();
    }

    private void initButton(Skin gamePadSkin, int screenWidth, int screenHeight) {

        Drawable actionButtonBackground = gamePadSkin.getDrawable("actionButton");

        float buttonDiameter = screenWidth/BUTTON_DIAMETER_DIVISOR;
        float buttonYpos = screenHeight/BUTTON_POSITON_DIVISOR;
        float buttonXPos = screenWidth - buttonYpos;

        actionButtonBackground.setMinWidth(buttonDiameter);
        actionButtonBackground.setMinHeight(buttonDiameter);

        this.actionButton = new ImageButton(actionButtonBackground);
        this.actionButton.setBounds(
                buttonXPos - buttonDiameter, buttonYpos,
                buttonDiameter, buttonDiameter
        );

        this.stage.addActor(this.actionButton);
    }

    private void initTouchPad(Skin gamePadSkin, int screenWidth, int screenHeight) {

        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        Drawable touchBackground = gamePadSkin.getDrawable("background");
        Drawable touchKnob = gamePadSkin.getDrawable("knob");
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;

        float touchPadDiameter = screenWidth/TOUCH_PAD_DIAMETER_DIVISOR;
        float touchPadXYPos = screenHeight/TOUCH_PAD_POSITON_DIVISOR;

        touchKnob.setMinWidth(touchPadDiameter / 2f);
        touchKnob.setMinHeight(touchPadDiameter / 2f);

        this.touchpad = new Touchpad(10, touchpadStyle);
        this.touchpad.setBounds(touchPadXYPos, touchPadXYPos, touchPadDiameter, touchPadDiameter);

        this.stage.addActor(this.touchpad);
    }
}
