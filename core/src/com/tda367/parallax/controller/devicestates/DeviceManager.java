package com.tda367.parallax.controller.devicestates;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.CardBoardApplicationListener;

public final class DeviceManager {

    private static Device device;

    private DeviceManager() {}

    public static Device getDevice() {
        return device;
    }

    public static void setDevice(ApplicationListener application) {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            device = new DesktopDevice();
        } else if (application instanceof CardBoardApplicationListener) {
            device = new CardboardDevice();
        } else if (Gdx.app.getType() == Application.ApplicationType.Android) {
            device = new AndroidDevice();
        }
    }
}
