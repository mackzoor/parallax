package com.tda367.parallax.model.gamepads;

/**
 * Class that bestow the button codes for an Android controller
 */

public final class AndroidGamePad implements GamePad {

    private static final int A_BUTTON = 96;
    private static final int B_BUTTON = 97;
    private static final int START_BUTTON = 108;
    private static final int DPAD_UP = 19;
    private static final int DPAD_RIGHT = 22;
    private static final int DPAD_DOWN = 20;
    private static final int DPAD_LEFT = 21;
    private static final int LEFT_JOYSTICK_X = 0;
    private static final int LEFT_JOYSTICK_Y = 1;

    @Override
    public int getActionButtonCode() {
        return A_BUTTON;
    }

    @Override
    public int getSecondaryActionButtonCode() {
        return B_BUTTON;
    }

    @Override
    public int getPauseButtonCode() {
        return START_BUTTON;
    }

    @Override
    public int getUpButtonCode() {
        return DPAD_UP;
    }

    @Override
    public int getRightButtonCode() {
        return DPAD_RIGHT;
    }

    @Override
    public int getDownButtonCode() {
        return DPAD_DOWN;
    }

    @Override
    public int getLeftButtonCode() {
        return DPAD_LEFT;
    }

    @Override
    public int getXAxisJoystickCode() {
        return LEFT_JOYSTICK_X;
    }

    @Override
    public int getYAxisJoystickCode() {
        return LEFT_JOYSTICK_Y;
    }

    @Override
    public float xAxisValueConverter(float value) {
        return value;
    }

    @Override
    public float yAxisValueConverter(float value) {
        return -1f * value;
    }
}
