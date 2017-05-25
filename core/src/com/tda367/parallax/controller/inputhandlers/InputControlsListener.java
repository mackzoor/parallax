package com.tda367.parallax.controller.inputhandlers;

public interface InputControlsListener {
    void actionButtonPressed();

    void secondaryActionButtonPressed();

    void pauseButtonPressed();

    void upButtonDown();

    void upButtonUp();

    void rightButtonDown();

    void rightButtonUp();

    void downButtonDown();

    void downButtonUp();

    void leftButtonDown();

    void leftButtonUp();

    void xAxisJoystickMovement(float xValue);

    void yAxisJoystickMovement(float yValue);

    void onScreenClick(int xValue, int yValue);

    ControllerMode getControllerMode();
}
