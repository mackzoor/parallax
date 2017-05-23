package com.tda367.parallax.controller.controllerclasses;

import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.controller.inputhandlers.ControllerMode;
import com.tda367.parallax.controller.inputhandlers.InputControlsAdapter;
import com.tda367.parallax.model.cardboardmenu.MainMenu;

/**
 * Controller class used for user input in the menu for Cardboard.
 */


public class CardboardMenuController extends InputControlsAdapter {

    private MainMenu mainMenu;

    public CardboardMenuController(MainMenu mainMenu, Device device){
        this.mainMenu = mainMenu;
        device.addInputDevices(this);
    }

    @Override
    public void actionButtonPressed() {
        mainMenu.action();
    }

    @Override
    public ControllerMode getControllerMode() {
        return ControllerMode.MENUMODE;
    }

}
