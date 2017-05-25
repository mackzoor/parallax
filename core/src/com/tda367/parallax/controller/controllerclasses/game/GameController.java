package com.tda367.parallax.controller.controllerclasses.game;

import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.controller.inputhandlers.ControllerMode;
import com.tda367.parallax.controller.inputhandlers.InputControlsAdapter;
import com.tda367.parallax.model.core.Parallax;
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
        this.spaceCraftController = new SpaceCraftController(parallax.getPlayer().getSpaceCraft());
    }

    @Override
    public void actionButtonPressed() {
        this.spaceCraftController.actionButtonPressed();
    }

    @Override
    public void secondaryActionButtonPressed() {
        this.parallaxView.setHudViewActive(!this.parallaxView.isHudViewActive());
    }

    @Override
    public void pauseButtonPressed() {
        this.parallax.setPaused(!this.parallax.isPaused());
        //TODO Add a "pause screen" to parallaxView
    }

    @Override
    public void upButtonDown() {
        this.spaceCraftController.upButtonDown();
    }

    @Override
    public void upButtonUp() {
        this.spaceCraftController.upButtonUp();
    }

    @Override
    public void rightButtonDown() {
        this.spaceCraftController.rightButtonDown();
    }

    @Override
    public void rightButtonUp() {
        this.spaceCraftController.rightButtonUp();
    }

    @Override
    public void downButtonDown() {
        this.spaceCraftController.downButtonDown();
    }

    @Override
    public void downButtonUp() {
        this.spaceCraftController.downButtonUp();
    }

    @Override
    public void leftButtonDown() {
        this.spaceCraftController.leftButtonDown();
    }

    @Override
    public void leftButtonUp() {
        this.spaceCraftController.leftButtonUp();
    }

    @Override
    public void xAxisJoystickMovement(float xValue) {
        this.spaceCraftController.xAxisJoystickMovement(xValue);
    }

    @Override
    public void yAxisJoystickMovement(float yValue) {
        this.spaceCraftController.yAxisJoystickMovement(yValue);
    }

    @Override
    public ControllerMode getControllerMode() {
        return ControllerMode.GAMEMODE;
    }
}
