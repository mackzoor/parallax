package com.tda367.parallax.platform.gamePadController;

/**
 * Created by Markus on 2017-04-22.
 */

public interface LibGdxGameController {
    void ActionButtonPressed();
    void SecondaryActionButtonPressed();
    void PauseButtonPressed();
    void UpButtonPressed();
    void RightButtonPressed();
    void DownButtonPressed();
    void LeftButtonPressed();
    void XAxisJoystickMovement(float value);
    void YAxisJoystickMovement(float value);
}
