package com.tda367.parallax.controller.inputhandlers.gamepads;

public final class AndroidGamePad implements GamePad {

    //Button codes can be found at:
    //https://developer.android.com/training/game-controllers/controller-input.html

    @Override
    public int getActionButtonCode() {
        return 96;
    }

    @Override
    public int getSecondaryActionButtonCode() {
        return 97;
    }

    @Override
    public int getPauseButtonCode() {
        return 108;
    }

    @Override
    public int getUpButtonCode() {
        return 19;
    }

    @Override
    public int getRightButtonCode() {
        return 22;
    }

    @Override
    public int getDownButtonCode() {
        return 20;
    }

    @Override
    public int getLeftButtonCode() {
        return 21;
    }

    @Override
    public int getXAxisJoystickCode() {
        return 0;
    }

    @Override
    public int getYAxisJoystickCode() {
        return 1;
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
