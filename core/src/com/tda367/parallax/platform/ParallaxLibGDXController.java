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
        this.panSpeed = 45;
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
        parallax.getPlayer().getSpaceCraft().setPanAcceleration(new Vector2f(0,panSpeed));
    }

    @Override
    public void upButtonUp() {
        parallax.getPlayer().getSpaceCraft().setPanAcceleration(new Vector2f(0,-0));
    }

    @Override
    public void rightButtonPressed() {
        parallax.getPlayer().getSpaceCraft().setPanAcceleration(new Vector2f(panSpeed,0));
    }

    @Override
    public void rightButtonUp() {
        parallax.getPlayer().getSpaceCraft().setPanAcceleration(new Vector2f(-0,0));
    }

    @Override
    public void downButtonPressed() {
        parallax.getPlayer().getSpaceCraft().setPanAcceleration(new Vector2f(0,-panSpeed));
    }

    @Override
    public void downButtonUp() {
        parallax.getPlayer().getSpaceCraft().setPanAcceleration(new Vector2f(0,0));
    }

    @Override
    public void leftButtonPressed() {
        parallax.getPlayer().getSpaceCraft().setPanAcceleration(new Vector2f(-panSpeed,0));
    }

    @Override
    public void leftButtonUp() {
        parallax.getPlayer().getSpaceCraft().setPanAcceleration(new Vector2f(0,0));
    }

    private float yValue;
    private float xValue;

    @Override
    public void xAxisJoystickMovement(float xValue) {
        xValue = xValue*panSpeed;
        this.xValue = xValue;
        parallax.getPlayer().getSpaceCraft().setPanAcceleration(xValue, yValue);
    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        yValue = yValue*panSpeed;
        this.yValue = yValue;
        parallax.getPlayer().getSpaceCraft().setPanAcceleration(xValue, yValue);
    }
}
