package com.tda367.parallax.controller.inputhandlers.gamepads;

/**
 * Class that bestow the button codes for a Playstation 3 controller
 */

public final class Playstation3GamePad implements GamePad {

    private static final int X_BUTTON = 14;
    private static final int CIRCLE_BUTTON = 13;
    private static final int START_BUTTON = 3;
    private static final int DPAD_UP = 4;
    private static final int DPAD_RIGHT = 5;
    private static final int DPAD_DOWN = 6;
    private static final int DPAD_LEFT = 7;
    private static final int LEFT_JOYSTICK_X = 0;
    private static final int LEFT_JOYSTICK_Y = 1;

    @Override
    public int getActionButtonCode() {
        return X_BUTTON;
    }

    @Override
    public int getSecondaryActionButtonCode() {
        return CIRCLE_BUTTON;
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
