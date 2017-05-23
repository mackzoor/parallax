package com.tda367.parallax;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.model.core.Player;

public class Main extends Game {

    @Override
    public void create(){
        Player player = new Player();
        DeviceManager.setDevice(this);
        GameStateManager.setMainMenuScreen(this,player);
    }

    @Override
    public void render(){
        screen.render(Gdx.graphics.getDeltaTime());
    }
}
