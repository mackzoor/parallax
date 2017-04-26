package com.tda367.parallax.platform;

import com.tda367.parallax.platform.inputControllers.InputControlsListener;

/**
 * Created by Markus on 2017-04-25.
 */

public interface GameModeState {
    public void addInputDevices(InputControlsListener controller);
}
