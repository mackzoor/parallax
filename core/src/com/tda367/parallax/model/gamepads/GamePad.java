package com.tda367.parallax.model.gamepads;

public interface GamePad {
    int getActionButtonCode();
    int getSecondaryActionButtonCode();
    int getPauseButtonCode();
    int getUpButtonCode();
    int getRightButtonCode();
    int getDownButtonCode();
    int getLeftButtonCode();
    int getXAxisJoystickCode();
    int getYAxisJoystickCode();
    float xAxisValueConverter(float value);
    float yAxisValueConverter(float value);
}
