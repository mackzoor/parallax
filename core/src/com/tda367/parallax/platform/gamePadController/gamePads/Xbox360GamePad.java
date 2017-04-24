package com.tda367.parallax.platform.gamePadController.gamePads;

import com.badlogic.gdx.controllers.PovDirection;

/**
 * Created by Markus on 2017-04-12.
 */
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
    public int GetActionButtonCode() {
        return 0;
    }

    @Override
    public int GetSecondaryActionButtonCode() {
        return 1;
    }

    @Override
    public int GetPauseButtonCode() {
        return 7;
    }

    @Override
    public int GetUpButtonCode() {
        return 99;
    } //Unknown atm

    @Override
    public int GetRightButtonCode() {
        return 99;
    } //Unknown atm

    @Override
    public int GetDownButtonCode() {
        return 99;
    } //Unknown atm

    @Override
    public int GetLeftButtonCode() {
        return 99;
    } //Unknown atm

    @Override
    public int GetXAxisJoystickCode() {
        return 1;
    }

    @Override
    public int GetYAxisJoystickCode() {
        return 0;
    }

    @Override
    public float XAxisValueConverter(float value) {
        return value;
    }

    @Override
    public float YAxisValueConverter(float value) {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null && getClass() == o.getClass());
    }
}
