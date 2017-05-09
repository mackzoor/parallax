package com.tda367.parallax.controller.inputcontrollers.gamepads;

public interface GamePad {
    int GetActionButtonCode();
    int GetSecondaryActionButtonCode();
    int GetPauseButtonCode();
    int GetUpButtonCode();
    int GetRightButtonCode();
    int GetDownButtonCode();
    int GetLeftButtonCode();
    int GetXAxisJoystickCode();
    int GetYAxisJoystickCode();
    float XAxisValueConverter(float value);
    float YAxisValueConverter(float value);
}