package com.tda367.parallax.platform.gamePadController.gamePads;

import com.badlogic.gdx.controllers.PovDirection;

/**
 * Created by Markus on 2017-04-12.
 */
public class Xbox360GamePad implements GamePad {
    public static final int BUTTON_X = 2;
    public static final int BUTTON_Y = 3;
    public static final int BUTTON_A = 0;
    public static final int BUTTON_B = 1;
    public static final int BUTTON_BACK = 6;
    public static final int BUTTON_START = 7;
    public static final PovDirection BUTTON_DPAD_UP = PovDirection.north;
    public static final PovDirection BUTTON_DPAD_DOWN = PovDirection.south;
    public static final PovDirection BUTTON_DPAD_RIGHT = PovDirection.east;
    public static final PovDirection BUTTON_DPAD_LEFT = PovDirection.west;
    public static final int BUTTON_LB = 4;
    public static final int BUTTON_L3 = 8;
    public static final int BUTTON_RB = 5;
    public static final int BUTTON_R3 = 9;
    public static final int AXIS_LEFT_X = 1; //-1 is left | +1 is right
    public static final int AXIS_LEFT_Y = 0; //-1 is up | +1 is down
    public static final int AXIS_LEFT_TRIGGER = 4; //value 0 to 1f
    public static final int AXIS_RIGHT_X = 3; //-1 is left | +1 is right
    public static final int AXIS_RIGHT_Y = 2; //-1 is up | +1 is down
    public static final int AXIS_RIGHT_TRIGGER = 4; //value 0 to -1f

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
    public int XAxisValueConverter(float value) {
        return 0;
    }

    @Override
    public int YAxisValueConverter(float value) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null && getClass() == o.getClass());
    }
}
