package com.tda367.parallax.platform.gamePadController.gamePads;

/**
 * Created by Markus on 2017-04-24.
 */

public class AndroidGamePad implements GamePad {
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
