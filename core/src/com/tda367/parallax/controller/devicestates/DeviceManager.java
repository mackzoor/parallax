package com.tda367.parallax.controller.devicestates;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.CardBoardApplicationListener;

public class DeviceManager {

    private static Device device;

    public static Device getGameModeState(ApplicationListener application) {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            return new DesktopDevice();
        } else if (application instanceof CardBoardApplicationListener) {
            return new CardboardDevice();
        } else if (Gdx.app.getType() == Application.ApplicationType.Android) {
            return new AndroidDevice();
        } else {
            return null;
        }
    }

    public static Device getDevice() {
        return device;
    }

    //Should perhaps throw an exception if device is unknown
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
