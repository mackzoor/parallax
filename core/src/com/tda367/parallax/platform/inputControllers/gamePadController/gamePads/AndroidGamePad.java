package com.tda367.parallax.platform.inputControllers.gamePadController.gamePads;

/**
 * Created by Markus on 2017-04-24.
 */

public class AndroidGamePad implements GamePad {

    //Button codes can be found at:
    //https://developer.android.com/training/game-controllers/controller-input.html

    @Override
    public int GetActionButtonCode() {
        return 96;
    }

    @Override
    public int GetSecondaryActionButtonCode() {
        return 97;
    }

    @Override
    public int GetPauseButtonCode() {
        return 108;
    }

    @Override
    public int GetUpButtonCode() {
        return 19;
    }

    @Override
    public int GetRightButtonCode() {
        return 22;
    }

    @Override
    public int GetDownButtonCode() {
        return 20;
    }

    @Override
    public int GetLeftButtonCode() {
        return 21;
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
