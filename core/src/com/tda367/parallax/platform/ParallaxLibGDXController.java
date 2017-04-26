package com.tda367.parallax.platform;

import com.tda367.parallax.parallaxCore.Parallax;
import com.tda367.parallax.platform.inputControllers.InputControlsListener;

/**
 * Created by Markus on 2017-04-11.
 */
class ParallaxLibGDXController implements InputControlsListener {

    private Parallax parallax;
    private float panSpeed;

    ParallaxLibGDXController(Parallax parallax, com.tda367.parallax.platform.gameModeStates.GameModeState gameModeState) {
        gameModeState.addInputDevices(this);
        this.parallax = parallax;
        this.panSpeed = 5f;
    }

//    void drawTouchpad(){
//        touchHandler.drawTouchpad();
//    }

    @Override
    public void actionButtonPressed() {
        parallax.getPlayer().getSpaceCraft().action();
    }

    @Override
    public void secondaryActionButtonPressed() {

    }

    @Override
    public void pauseButtonPressed() {

    }

    @Override
    public void upButtonPressed() {
        float xValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getX();
        float yValue = panSpeed;
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void upButtonUp() {
        float xValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getX();
        float yValue = 0;
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void rightButtonPressed() {
        float xValue = panSpeed;
        float yValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getY();
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void rightButtonUp() {
        float xValue = 0;
        float yValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getY();
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void downButtonPressed() {
        float xValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getX();
        float yValue = -panSpeed;
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void downButtonUp() {
        float xValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getX();
        float yValue = 0;
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void leftButtonPressed() {
        float xValue = -panSpeed;
        float yValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getY();
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void leftButtonUp() {
        float xValue = 0;
        float yValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getY();
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void xAxisJoystickMovement(float xValue) {
        xValue = xValue * panSpeed;
        float yValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getY();
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        yValue = yValue * panSpeed;
        float xValue = parallax.getPlayer().getSpaceCraft().getPanVelocity().getX();
        parallax.getPlayer().getSpaceCraft().setPanVelocity(xValue, yValue);
    }
}
