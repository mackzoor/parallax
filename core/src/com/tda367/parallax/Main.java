package com.tda367.parallax;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tda367.parallax.controller.screens.ScreenState;
import com.tda367.parallax.controller.ScreenManager;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.model.core.Player;

public class Main extends Game {

    @Override
    public void create(){
        Gdx.input.setCatchBackKey(true);
        Player player = new Player();
        DeviceManager.setDevice(this);
        ScreenManager.setGame(this);
        ScreenManager.setGameState(ScreenState.MAIN_MENU, player);
    }

    @Override
    public void render(){
        screen.render(Gdx.graphics.getDeltaTime());
    }
}
