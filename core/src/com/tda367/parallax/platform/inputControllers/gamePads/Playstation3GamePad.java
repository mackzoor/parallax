package com.tda367.parallax.platform.inputControllers.gamePads;

/**
 * Created by Markus on 2017-04-12.
 */
public class Playstation3GamePad implements com.tda367.parallax.platform.inputControllers.gamePads.GamePad {
    /*
    All of the key codes are listed below:
    BUTTON_X = 14
    BUTTON_CIRCLE = 13
    BUTTON_TRIANGLE = 12
    BUTTON_SQUARE = 15
    BUTTON_L1 = 10
    BUTTON_L2 = 8
    BUTTON_L3 = 1
    BUTTON_R1 = 11
    BUTTON_R2 = 9
    BUTTON_R3 = 2
    BUTTON_PAD_DOWN = 6
    BUTTON_PAD_RIGHT = 5
    BUTTON_PAD_UP = 4
    BUTTON_PAD_LEFT = 7
    BUTTON_SELECT = 0
    BUTTON_START = 3
    BUTTON_PS = 16
    AXIS_LEFT_X = 0
    AXIS_LEFT_Y = 1
    AXIS_RIGHT_X = 2
    AXIS_RIGHT_Y = 3
     */

    @Override
    public int GetActionButtonCode() {
        return 14;
    }

    @Override
    public int GetSecondaryActionButtonCode() {
        return 13;
    }

    @Override
    public int GetPauseButtonCode() {
        return 3;
    }

    @Override
    public int GetUpButtonCode() {
        return 4;
    }

    @Override
    public int GetRightButtonCode() {
        return 5;
    }

    @Override
    public int GetDownButtonCode() {
        return 6;
    }

    @Override
    public int GetLeftButtonCode() {
        return 7;
    }

    @Override
    public int GetXAxisJoystickCode() {
        return 0;
    }

    @Override
    public int GetYAxisJoystickCode() {
        return 1;
    }

    @Override
    public float XAxisValueConverter(float value) {
        return value;
    }

    @Override
    public float YAxisValueConverter(float value) {
        return -1f * value;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null && getClass() == o.getClass());
    }
}
