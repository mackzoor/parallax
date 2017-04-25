package com.tda367.parallax.platform.inputControllers.gamePadController;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.tda367.parallax.platform.inputControllers.LibGdxGameController;
import com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.GamePad;
import com.tda367.parallax.platform.inputControllers.gamePadController.gamePads.GamePadFactory;

/**
 * Created by Markus on 2017-04-22.
 */

public class LibGdxGamePadHandler implements ControllerListener {

    private LibGdxGameController gameController;
    private Controller controller;
    private GamePad gamePad;

    public LibGdxGamePadHandler(LibGdxGameController gameController) {
        this.gameController = gameController;
        Controllers.addListener(this);
        for (Controller controller : Controllers.getControllers()) {
            System.out.println(controller.getName());
            this.controller = controller;
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

        if(buttonCode == gamePad.GetActionButtonCode()) {
            gameController.actionButtonPressed();
        } else if (buttonCode == gamePad.GetSecondaryActionButtonCode()) {
            gameController.secondaryActionButtonPressed();
        } else if (buttonCode == gamePad.GetLeftButtonCode()) {
            gameController.leftButtonPressed();
        } else if (buttonCode == gamePad.GetUpButtonCode()) {
            gameController.upButtonPressed();
        } else if (buttonCode == gamePad.GetRightButtonCode()) {
            gameController.rightButtonPressed();
        } else if (buttonCode == gamePad.GetDownButtonCode()) {
            gameController.downButtonPressed();
        } else if (buttonCode == gamePad.GetPauseButtonCode()) {
            gameController.pauseButtonPressed();
        }

        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {

        if (buttonCode == gamePad.GetLeftButtonCode()) {
            gameController.leftButtonUp();
        } else if (buttonCode == gamePad.GetUpButtonCode()) {
            gameController.upButtonUp();
        } else if (buttonCode == gamePad.GetRightButtonCode()) {
            gameController.rightButtonUp();
        } else if (buttonCode == gamePad.GetDownButtonCode()) {
            gameController.downButtonUp();
        }

        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {

        if(axisCode == gamePad.GetXAxisJoystickCode()) {
            gameController.xAxisJoystickMovement(gamePad.XAxisValueConverter(value));
        } else if (axisCode == gamePad.GetYAxisJoystickCode()) {
            gameController.yAxisJoystickMovement(gamePad.YAxisValueConverter(value));
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
