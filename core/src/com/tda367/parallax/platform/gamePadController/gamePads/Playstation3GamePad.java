package com.tda367.parallax.platform.gamePadController.gamePads;

/**
 * Created by Markus on 2017-04-12.
 */
public class Playstation3GamePad implements GamePad {
    public static final int BUTTON_X = 14;
    public static final int BUTTON_CIRCLE = 13;
    public static final int BUTTON_TRIANGLE = 12;
    public static final int BUTTON_SQUARE = 15;
    public static final int BUTTON_L1 = 10;
    public static final int BUTTON_L2 = 8;
    public static final int BUTTON_L3 = 1;
    public static final int BUTTON_R1 = 11;
    public static final int BUTTON_R2 = 9;
    public static final int BUTTON_R3 = 2;
    public static final int BUTTON_PAD_DOWN = 6;
    public static final int BUTTON_PAD_RIGHT = 5;
    public static final int BUTTON_PAD_UP = 4;
    public static final int BUTTON_PAD_LEFT = 7;
    public static final int BUTTON_SELECT = 0;
    public static final int BUTTON_START = 3;
    public static final int BUTTON_PS = 16;
    public static final int AXIS_LEFT_X = 0; //-1 is left | +1 is right
    public static final int AXIS_LEFT_Y = 1; //-1 is up | +1 is down
    public static final int AXIS_RIGHT_X = 2; //-1 is left | +1 is right
    public static final int AXIS_RIGHT_Y = 3; //-1 is up | +1 is down

    @Override
    public int GetActionButtonCode() {
        return 0;
    }

    @Override
    public int GetSecondaryActionButtonCode() {
        return 0;
    }

    @Override
    public int GetPauseButtonCode() {
        return 0;
    }

    @Override
    public int GetUpButtonCode() {
        return 0;
    }

    @Override
    public int GetRightButtonCode() {
        return 0;
    }

    @Override
    public int GetDownButtonCode() {
        return 0;
    }

    @Override
    public int GetLeftButtonCode() {
        return 0;
    }

    @Override
    public int GetXAxisJoystickCode() {
        return 0;
    }

    @Override
    public int GetYAxisJoystickCode() {
        return 0;
    }

    @Override
    public int XAxisValueConverter(int value) {
        return 0;
    }

    @Override
    public int YAxisValueConverter(int value) {
        return 0;
    }
}
