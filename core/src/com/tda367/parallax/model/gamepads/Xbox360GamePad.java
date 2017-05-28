package com.tda367.parallax.model.gamepads;

/**
 * Class that bestow the button codes for a Xbox360 controller.
 */

public final class Xbox360GamePad implements GamePad {

    private static final int A_BUTTON = 0;
    private static final int B_BUTTON = 1;
    private static final int START_BUTTON = 7;

    private static final int DPAD_UP = 999; //Unknown
    private static final int DPAD_RIGHT = 999; //Unknown
    private static final int DPAD_DOWN = 999; //Unknown
    private static final int DPAD_LEFT = 999; //Unknown

    private static final int LEFT_JOYSTICK_X = 1;
    private static final int LEFT_JOYSTICK_Y = 0;

    Xbox360GamePad() {

    }

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
        return -value;
    }
}
