package com.tda367.parallax.platform.inputControllers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.tda367.parallax.platform.inputControllers.gamePads.GamePad;
import com.tda367.parallax.platform.inputControllers.gamePads.GamePadFactory;

/**
 * Created by Markus on 2017-04-22.
 */

public class LibGdxGamePadHandler implements ControllerListener {

    private InputControlsListener listener;
    private GamePad gamePad;

    public LibGdxGamePadHandler() {
        Controllers.addListener(this);
        for (Controller controller : Controllers.getControllers()) {
            System.out.println(controller.getName());
            controller.addListener(this);
            connected(controller);
        }
    }

    public void setListener(InputControlsListener listener) {
        this.listener = listener;
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

        if(listener != null) {
            if(buttonCode == gamePad.GetActionButtonCode()) {
                listener.actionButtonPressed();
            } else if (buttonCode == gamePad.GetSecondaryActionButtonCode()) {
                listener.secondaryActionButtonPressed();
            } else if (buttonCode == gamePad.GetLeftButtonCode()) {
                listener.leftButtonPressed();
            } else if (buttonCode == gamePad.GetUpButtonCode()) {
                listener.upButtonPressed();
            } else if (buttonCode == gamePad.GetRightButtonCode()) {
                listener.rightButtonPressed();
            } else if (buttonCode == gamePad.GetDownButtonCode()) {
                listener.downButtonPressed();
            } else if (buttonCode == gamePad.GetPauseButtonCode()) {
                listener.pauseButtonPressed();
            }
        }

        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {

        if (listener != null) {
            if (buttonCode == gamePad.GetLeftButtonCode()) {
                listener.leftButtonUp();
            } else if (buttonCode == gamePad.GetUpButtonCode()) {
                listener.upButtonUp();
            } else if (buttonCode == gamePad.GetRightButtonCode()) {
                listener.rightButtonUp();
            } else if (buttonCode == gamePad.GetDownButtonCode()) {
                listener.downButtonUp();
            }
        }

        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {

        if (listener != null) {
            if(axisCode == gamePad.GetXAxisJoystickCode()) {
                listener.xAxisJoystickMovement(gamePad.XAxisValueConverter(value));
            } else if (axisCode == gamePad.GetYAxisJoystickCode()) {
                listener.yAxisJoystickMovement(gamePad.YAxisValueConverter(value));
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
