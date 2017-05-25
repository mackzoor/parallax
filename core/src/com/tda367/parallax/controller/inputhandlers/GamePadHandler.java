package com.tda367.parallax.controller.inputhandlers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.tda367.parallax.model.gamepads.GamePad;
import com.tda367.parallax.model.gamepads.GamePadFactory;
import lombok.Setter;

public final class GamePadHandler extends ControllerAdapter {

    /**
     * Class that checks inputs from a game pad, compares it with a buttonCode and notifies its
     * observer, an {@link InputControlsListener}, about it.
     */

    @Setter private InputControlsListener listener;
    private GamePad gamePad;

    public GamePadHandler() {
        Controllers.addListener(this);
        for (Controller controller : Controllers.getControllers()) {
            controller.addListener(this);
            connected(controller);
        }
    }

    @Override
    public void connected(Controller controller) {
        gamePad = GamePadFactory.getGamePad(controller.getName());
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {

        if (listener != null) {
            if (buttonCode == gamePad.getActionButtonCode()) {
                listener.actionButtonPressed();
            } else if (buttonCode == gamePad.getSecondaryActionButtonCode()) {
                listener.secondaryActionButtonPressed();
            } else if (buttonCode == gamePad.getPauseButtonCode()) {
                listener.pauseButtonPressed();
            }
            checkDirectionButtons(buttonCode);
        }

        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {

        if (listener != null) {
            if (buttonCode == gamePad.getLeftButtonCode()) {
                listener.leftButtonUp();
            } else if (buttonCode == gamePad.getUpButtonCode()) {
                listener.upButtonUp();
            } else if (buttonCode == gamePad.getRightButtonCode()) {
                listener.rightButtonUp();
            } else if (buttonCode == gamePad.getDownButtonCode()) {
                listener.downButtonUp();
            }
        }

        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {

        Float roundedValue = value;

        if (value * value < 0.02f){
            roundedValue = 0f;
        }

        if (listener != null) {
            if (axisCode == gamePad.getXAxisJoystickCode()) {
                listener.xAxisJoystickMovement(gamePad.xAxisValueConverter(roundedValue));
                return false;
            }

            if (axisCode == gamePad.getYAxisJoystickCode()) {
                listener.yAxisJoystickMovement(gamePad.yAxisValueConverter(roundedValue));
                return false;
            }
        }

        return false;
    }

    private void checkDirectionButtons(int buttonCode) {
        if (buttonCode == gamePad.getLeftButtonCode()) {
            listener.leftButtonDown();
        } else if (buttonCode == gamePad.getUpButtonCode()) {
            listener.upButtonDown();
        } else if (buttonCode == gamePad.getRightButtonCode()) {
            listener.rightButtonDown();
        } else if (buttonCode == gamePad.getDownButtonCode()) {
            listener.downButtonDown();
        }
    }
}
