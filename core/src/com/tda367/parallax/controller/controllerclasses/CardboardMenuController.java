package com.tda367.parallax.controller.controllerclasses;

import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.controller.inputhandlers.ControllerMode;
import com.tda367.parallax.controller.inputhandlers.InputControlsAdapter;
import com.tda367.parallax.model.menu.MainMenu;

/**
 * Controller class used for user input in the menu for Cardboard.
 */


public class CardboardMenuController extends InputControlsAdapter {

    private final MainMenu mainMenu;

    public CardboardMenuController(MainMenu mainMenu, Device device) {
        super();
        this.mainMenu = mainMenu;
        device.addInputDevices(this);
    }

    @Override
    public void actionButtonPressed() {
        this.mainMenu.action();
    }

    @Override
    public ControllerMode getControllerMode() {
        return ControllerMode.MENUMODE;
    }

}
