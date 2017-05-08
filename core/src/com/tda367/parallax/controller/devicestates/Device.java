package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.controller.inputcontrollers.InputControlsListener;

public interface Device {
    void addInputDevices(InputControlsListener controller);
    void update();
}
