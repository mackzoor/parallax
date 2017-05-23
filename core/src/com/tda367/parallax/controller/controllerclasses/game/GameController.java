package com.tda367.parallax.controller.controllerclasses.game;

import com.tda367.parallax.controller.inputhandlers.ControllerMode;
import com.tda367.parallax.controller.inputhandlers.InputControlsAdapter;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.view.parallaxview.ParallaxView;

/**
 * Controller class used for user input during game play.
 */

public class GameController extends InputControlsAdapter {

    private SpaceCraftController spaceCraftController;

    private Parallax parallax;
    private ParallaxView parallaxView;

    public GameController(Parallax parallax, ParallaxView parallaxView, Device device) {
        device.addInputDevices(this);
        this.parallax = parallax;
        this.parallaxView = parallaxView;
        spaceCraftController = new SpaceCraftController(parallax.getPlayer().getSpaceCraft());
    }

    @Override
    public void actionButtonPressed() {
        spaceCraftController.actionButtonPressed();
    }

    @Override
    public void secondaryActionButtonPressed() {
        parallaxView.setHudViewActive(!parallaxView.isHudViewActive());
    }

    @Override
    public void pauseButtonPressed() {
        parallax.setPaused(!parallax.isPaused());
        //TODO Add a "pause screen" to parallaxView
    }

    @Override
    public void upButtonDown() {
        spaceCraftController.upButtonDown();
    }

    @Override
    public void upButtonUp() {
        spaceCraftController.upButtonUp();
    }

    @Override
    public void rightButtonDown() {
        spaceCraftController.rightButtonDown();
    }

    @Override
    public void rightButtonUp() {
        spaceCraftController.rightButtonUp();
    }

    @Override
    public void downButtonDown() {
        spaceCraftController.downButtonDown();
    }

    @Override
    public void downButtonUp() {
        spaceCraftController.downButtonUp();
    }

    @Override
    public void leftButtonDown() {
        spaceCraftController.leftButtonDown();
    }

    @Override
    public void leftButtonUp() {
        spaceCraftController.leftButtonUp();
    }

    @Override
    public void xAxisJoystickMovement(float xValue) {
        spaceCraftController.xAxisJoystickMovement(xValue);
    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        spaceCraftController.yAxisJoystickMovement(yValue);
    }

    @Override
    public ControllerMode getControllerMode() {
        return ControllerMode.GAMEMODE;
    }
}
