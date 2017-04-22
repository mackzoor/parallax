package com.tda367.parallax.platform.gamePadController;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.tda367.parallax.platform.gamePadController.gamePads.GamePad;
import com.tda367.parallax.platform.gamePadController.gamePads.GamePadFactory;

/**
 * Created by Markus on 2017-04-22.
 */

public class LibGdxGamePadHandler implements ControllerListener {

    private LibGdxGameController controller;
    private GamePad gamePad;

    public LibGdxGamePadHandler(LibGdxGameController controller) {
        this.controller = controller;
    }

    public void setController(LibGdxGameController controller) {
        this.controller = controller;
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
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }

    //The following commands won't be used in our game, but can easily be included in the future

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

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
