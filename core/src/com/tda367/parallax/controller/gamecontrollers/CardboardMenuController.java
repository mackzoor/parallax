package com.tda367.parallax.controller.gamecontrollers;

import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;
import com.tda367.parallax.model.cardboardmenu.CardboardMainMenu;


public class CardboardMenuController extends ControllerAdapter {

    CardboardMainMenu cardboardMainMenu;

    public CardboardMenuController(CardboardMainMenu mainMenu, Device device){
        cardboardMainMenu = mainMenu;
        device.addInputDevices(this);
    }

    @Override
    public void actionButtonPressed() {
        cardboardMainMenu.action();
    }

}
