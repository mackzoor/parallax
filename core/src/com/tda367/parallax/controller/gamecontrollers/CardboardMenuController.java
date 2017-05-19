package com.tda367.parallax.controller.gamecontrollers;

import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.model.cardboardmenu.MainMenu;


public class CardboardMenuController extends ControllerAdapter {

    MainMenu mainMenu;

    public CardboardMenuController(MainMenu mainMenu, Device device){
        this.mainMenu = mainMenu;
        device.addInputDevices(this);
    }

    @Override
    public void actionButtonPressed() {
        mainMenu.action();
    }

}
