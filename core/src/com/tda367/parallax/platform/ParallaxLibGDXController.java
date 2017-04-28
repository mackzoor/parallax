package com.tda367.parallax.platform;

import com.tda367.parallax.parallaxCore.Parallax;
import com.tda367.parallax.platform.inputControllers.InputControlsListener;

import javax.vecmath.Vector2f;

/**
 * Created by Markus on 2017-04-11.
 */
class ParallaxLibGDXController implements InputControlsListener {

    private Parallax parallax;
    private float panSpeed;

    ParallaxLibGDXController(Parallax parallax, com.tda367.parallax.platform.gameModeStates.GameModeState gameModeState) {
        gameModeState.addInputDevices(this);
        this.parallax = parallax;
        this.panSpeed = 1;
    }

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
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(new Vector2f(0,panSpeed));
    }

    @Override
    public void upButtonUp() {
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(new Vector2f(0,-0));
    }

    @Override
    public void rightButtonPressed() {
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(new Vector2f(panSpeed,0));
    }

    @Override
    public void rightButtonUp() {
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(new Vector2f(-0,0));
    }

    @Override
    public void downButtonPressed() {
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(new Vector2f(0,-panSpeed));
    }

    @Override
    public void downButtonUp() {
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(new Vector2f(0,0));
    }

    @Override
    public void leftButtonPressed() {
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(new Vector2f(-panSpeed,0));
    }

    @Override
    public void leftButtonUp() {
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(new Vector2f(0,0));
    }

    private float yValue = 0;
    private float xValue = 0;

    @Override
    public void xAxisJoystickMovement(float xValue) {
        if (Math.abs(xValue) > 0.1){
            this.xValue = xValue;
        } else {
            this.xValue = 0;
        }
        updateJoysticks();
    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        if (Math.abs(yValue) > 0.1){
            this.yValue = yValue;
        } else {
            this.yValue = 0;
        }
        updateJoysticks();
    }

    private void updateJoysticks(){
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(xValue, yValue);
    }
}
