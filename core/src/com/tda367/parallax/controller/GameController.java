package com.tda367.parallax.controller;

import com.tda367.parallax.model.parallaxCore.Parallax;
import com.tda367.parallax.platform.GameStateManager;
import com.tda367.parallax.platform.gameModeStates.GameModeState;
import com.tda367.parallax.controller.inputControllers.InputControlsListener;

/**
 * Created by Markus on 2017-04-11.
 */
public class GameController implements InputControlsListener {

    private float yValue = 0;
    private float xValue = 0;

    private Parallax parallax;

    public GameController(Parallax parallax, GameModeState gameModeState) {
        gameModeState.addInputDevices(this);
        this.parallax = parallax;
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
        GameStateManager.getInstance().setState(GameStateManager.State.MAIN_MENU);
    }

    @Override
    public void upButtonDown() {
        yValue += 1;
        updateControls();
    }

    @Override
    public void upButtonUp() {
        yValue -= 1;
        updateControls();
    }

    @Override
    public void rightButtonDown() {
        xValue += 1;
        updateControls();
    }

    @Override
    public void rightButtonUp() {
        xValue -= 1;
        updateControls();
    }

    @Override
    public void downButtonDown() {
        yValue -= 1;
        updateControls();
    }

    @Override
    public void downButtonUp() {
        yValue += 1;
        updateControls();
    }

    @Override
    public void leftButtonDown() {
        xValue -= 1;
        updateControls();
    }

    @Override
    public void leftButtonUp() {
        xValue += 1;
        updateControls();
    }

    @Override
    public void xAxisJoystickMovement(float xValue) {
        this.xValue = xValue;
        updateControls();
    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        this.yValue = yValue;
        updateControls();
    }

    @Override
    public void onScreenClick(int xValue, int yValue) {

    }

    private synchronized void updateControls(){
        parallax.getPlayer().getSpaceCraft().setDesiredPanVelocity(xValue, yValue);
    }
}
