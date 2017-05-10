package com.tda367.parallax.controller.inputcontrollers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.tda367.parallax.controller.inputcontrollers.gamepads.GamePad;
import com.tda367.parallax.controller.inputcontrollers.gamepads.GamePadFactory;
import lombok.Setter;

public final class LibGdxGamePadHandler implements ControllerListener {

    @Setter private InputControlsListener listener;
    private GamePad gamePad;

    public LibGdxGamePadHandler() {
        Controllers.addListener(this);
        for (Controller controller : Controllers.getControllers()) {
            System.out.println(controller.getName());
            controller.addListener(this);
            connected(controller);
        }
    }

    @Override
    public void connected(Controller controller) {
        gamePad = GamePadFactory.getGamePad(controller.getName());
    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {

        if (listener != null) {
            if (buttonCode == gamePad.getActionButtonCode()) {
                listener.actionButtonPressed();
            } else if (buttonCode == gamePad.getSecondaryActionButtonCode()) {
                listener.secondaryActionButtonPressed();
            } else if (buttonCode == gamePad.getLeftButtonCode()) {
                listener.leftButtonDown();
            } else if (buttonCode == gamePad.getUpButtonCode()) {
                listener.upButtonDown();
            } else if (buttonCode == gamePad.getRightButtonCode()) {
                listener.rightButtonDown();
            } else if (buttonCode == gamePad.getDownButtonCode()) {
                listener.downButtonDown();
            } else if (buttonCode == gamePad.getPauseButtonCode()) {
                listener.pauseButtonPressed();
            }
        }

        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {

        if (listener != null) {
            if (buttonCode == gamePad.getLeftButtonCode()) {
                listener.leftButtonUp();
                return false;
            }

            if (buttonCode == gamePad.getUpButtonCode()) {
                listener.upButtonUp();
                return false;
            }

            if (buttonCode == gamePad.getRightButtonCode()) {
                listener.rightButtonUp();
                return false;
            }

            if (buttonCode == gamePad.getDownButtonCode()) {
                listener.downButtonUp();
                return false;
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
                listener.xAxisJoystickMovement(gamePad.xAxisValueConverter(value));
                return false;
            }

            if (axisCode == gamePad.getYAxisJoystickCode()) {
                listener.yAxisJoystickMovement(gamePad.yAxisValueConverter(value));
                return false;
            }
        }

        return false;
    }

    //The following commands won't be used in our game, but can easily be included in the future

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }
}
