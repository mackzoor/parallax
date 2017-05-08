package com.tda367.parallax;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tda367.parallax.controller.gamestates.GameStateManager;
import com.tda367.parallax.controller.devicestates.DeviceManager;

public class Main extends Game {

    @Override
    public void create() {
        DeviceManager.setDevice(this);
        GameStateManager.setMainMenuScreen(this);
    }

    @Override
    public void render() {
        screen.render(Gdx.graphics.getDeltaTime());
    }
}
