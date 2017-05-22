package com.tda367.parallax.controller.gamecontrollers;

import com.tda367.parallax.controller.devicestates.Device;
import com.tda367.parallax.controller.inputcontrollers.InputControlsAdapter;
import com.tda367.parallax.model.gameover.GameOverModel;


public class GameOverController extends InputControlsAdapter {

    private GameOverModel model;

    public GameOverController(GameOverModel model, Device device){
        this.model = model;
        device.addInputDevices(this);
    }

    @Override
    public void actionButtonPressed() {
        model.exit();
    }

    @Override
    public void onScreenClick(int xValue, int yValue) {
        model.exit();
    }
}
