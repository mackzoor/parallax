package com.tda367.parallax.platform.inputControllers;

/**
 * Created by Markus on 2017-04-22.
 */

public interface LibGdxGameController {
    void actionButtonPressed();
    void secondaryActionButtonPressed();
    void pauseButtonPressed();
    void upButtonPressed();
    void upButtonUp();
    void rightButtonPressed();
    void rightButtonUp();
    void downButtonPressed();
    void downButtonUp();
    void leftButtonPressed();
    void leftButtonUp();
    void xAxisJoystickMovement(float xValue);
    void yAxisJoystickMovement(float yValue);
}
