package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.controller.inputControllers.InputControlsListener;

/**
 * Created by Markus on 2017-04-25.
 */

public interface Device {
    public void addInputDevices(InputControlsListener controller);
    public void update();
}
