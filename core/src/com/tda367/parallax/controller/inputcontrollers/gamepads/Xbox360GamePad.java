package com.tda367.parallax.controller.inputcontrollers.gamepads;

public class Xbox360GamePad implements GamePad {
    /*
    All of the key codes are listed below:
    BUTTON_X = 2
    BUTTON_Y = 3
    BUTTON_A = 0
    BUTTON_B = 1
    BUTTON_BACK = 6
    BUTTON_START = 7
    BUTTON_DPAD_UP = PovDirection.north
    BUTTON_DPAD_DOWN = PovDirection.south
    BUTTON_DPAD_RIGHT = PovDirection.east
    BUTTON_DPAD_LEFT = PovDirection.west
    BUTTON_LB = 4
    BUTTON_L3 = 8
    BUTTON_RB = 5
    BUTTON_R3 = 9
    AXIS_LEFT_X = 1
    AXIS_LEFT_Y = 0
    AXIS_LEFT_TRIGGER = 4
    AXIS_RIGHT_X = 3
    AXIS_RIGHT_Y = 2
    AXIS_RIGHT_TRIGGER = 4
    */

    @Override
    public int getActionButtonCode() {
        return 0;
    }

    @Override
    public int getSecondaryActionButtonCode() {
        return 1;
    }

    @Override
    public int getPauseButtonCode() {
        return 7;
    }

    @Override
    public int getUpButtonCode() {
        return 99;
    } //Unknown atm

    @Override
    public int getRightButtonCode() {
        return 99;
    } //Unknown atm

    @Override
    public int getDownButtonCode() {
        return 99;
    } //Unknown atm

    @Override
    public int getLeftButtonCode() {
        return 99;
    } //Unknown atm

    @Override
    public int getXAxisJoystickCode() {
        return 1;
    }

    @Override
    public int getYAxisJoystickCode() {
        return 0;
    }

    @Override
    public float xAxisValueConverter(float value) {
        return value;
    }

    @Override
    public float yAxisValueConverter(float value) {
        return -value;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null && getClass() == o.getClass());
    }
}
