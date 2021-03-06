package com.tda367.parallax.controller.devicestates;

import com.tda367.parallax.controller.inputhandlers.InputControlsListener;

public interface Device {
    void addInputDevices(InputControlsListener controller);

    void update();
}
