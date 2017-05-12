package com.tda367.parallax.controller.gamecontrollers;

import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;
import com.tda367.parallax.model.cardboardmenu.CardboardMainMenu;

/**
 * Created by Rasmus on 2017-05-10.
 */
public class CardboardMenuController implements InputControlsListener {

    CardboardMainMenu cardboardMainMenu;

    public CardboardMenuController(CardboardMainMenu mainMenu, Device device){
        cardboardMainMenu = mainMenu;
        device.addInputDevices(this);
    }

    @Override
    public void actionButtonPressed() {
        cardboardMainMenu.action();
    }

    @Override
    public void secondaryActionButtonPressed() {

    }

    @Override
    public void pauseButtonPressed() {

    }

    @Override
    public void upButtonDown() {

    }

    @Override
    public void upButtonUp() {

    }

    @Override
    public void rightButtonDown() {

    }

    @Override
    public void rightButtonUp() {

    }

    @Override
    public void downButtonDown() {

    }

    @Override
    public void downButtonUp() {

    }

    @Override
    public void leftButtonDown() {

    }

    @Override
    public void leftButtonUp() {

    }

    @Override
    public void xAxisJoystickMovement(float xValue) {

    }

    @Override
    public void yAxisJoystickMovement(float yValue) {

    }

    @Override
    public void onScreenClick(int xValue, int yValue) {

    }
}
