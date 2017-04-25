package com.tda367.parallax.platform.inputControllers.gamePadController.gamePads;

/**
 * Created by Markus on 2017-04-22.
 */

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
