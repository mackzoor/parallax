package com.tda367.parallax.controller.inputhandlers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.tda367.parallax.model.gamepads.GamePad;
import com.tda367.parallax.model.gamepads.GamePadFactory;
import lombok.Setter;

/**
 * Class that checks inputs from a game pad, compares it with a buttonCode and notifies its
 * observer, an {@link InputControlsListener}, about it.
 */

public final class GamePadHandler extends ControllerAdapter {

    @Setter
    private InputControlsListener listener;
    private GamePad gamePad;

    public GamePadHandler() {
        super();
        Controllers.addListener(this);
        for (final Controller controller : Controllers.getControllers()) {
            controller.addListener(this);
            connected(controller);
        }
    }

    @Override
    public void connected(Controller controller) {
        this.gamePad = GamePadFactory.getGamePad(controller.getName());
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {

        if (this.listener != null) {
            if (buttonCode == this.gamePad.getActionButtonCode()) {
                this.listener.actionButtonPressed();
            } else if (buttonCode == this.gamePad.getSecondaryActionButtonCode()) {
                this.listener.secondaryActionButtonPressed();
            } else if (buttonCode == this.gamePad.getPauseButtonCode()) {
                this.listener.pauseButtonPressed();
            }
            checkDirectionButtons(buttonCode);
        }

        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {

        if (this.listener != null) {
            if (buttonCode == this.gamePad.getLeftButtonCode()) {
                this.listener.leftButtonUp();
            } else if (buttonCode == this.gamePad.getUpButtonCode()) {
                this.listener.upButtonUp();
            } else if (buttonCode == this.gamePad.getRightButtonCode()) {
                this.listener.rightButtonUp();
            } else if (buttonCode == this.gamePad.getDownButtonCode()) {
                this.listener.downButtonUp();
            }
        }

        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {

        Float roundedValue = value;

        if (value * value < 0.02f) {
            roundedValue = 0f;
        }

        if (this.listener != null) {
            if (axisCode == this.gamePad.getXAxisJoystickCode()) {
                this.listener.xAxisJoystickMovement(this.gamePad.xAxisValueConverter(roundedValue));
            } else if (axisCode == this.gamePad.getYAxisJoystickCode()) {
                this.listener.yAxisJoystickMovement(this.gamePad.yAxisValueConverter(roundedValue));
            }
        }

        return false;
    }

    private void checkDirectionButtons(int buttonCode) {
        if (buttonCode == this.gamePad.getLeftButtonCode()) {
            this.listener.leftButtonDown();
        } else if (buttonCode == this.gamePad.getUpButtonCode()) {
            this.listener.upButtonDown();
        } else if (buttonCode == this.gamePad.getRightButtonCode()) {
            this.listener.rightButtonDown();
        } else if (buttonCode == this.gamePad.getDownButtonCode()) {
            this.listener.downButtonDown();
        }
    }
}
