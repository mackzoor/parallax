package com.tda367.parallax;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tda367.parallax.controller.ScreenManager;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.screens.ScreenState;
import com.tda367.parallax.model.core.Player;

public class Main extends Game {

    @Override
    public void create() {
        Gdx.input.setCatchBackKey(true);
        final Player player = new Player("No name");
        DeviceManager.setDevice(this);

        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            ScreenManager.initialize(false);
        } else {
            ScreenManager.initialize(true);
        }
        final ScreenManager screenManager = ScreenManager.getInstance();
        screenManager.setGame(this);
        screenManager.setGameState(ScreenState.MAIN_MENU, player);
    }

    @Override
    public void render() {
        screen.render(Gdx.graphics.getDeltaTime());
    }
}
