package com.tda367.parallax.platform.gamemodestates;

import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;

/**
 * Created by Markus on 2017-04-25.
 */

public interface GameModeState {
    public void addInputDevices(InputControlsListener controller);
    public void update();
}
