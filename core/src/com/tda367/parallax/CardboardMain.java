package com.tda367.parallax;

import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.tda367.parallax.controller.GameStateManager;
import com.tda367.parallax.controller.devicestates.DeviceManager;
import com.tda367.parallax.controller.gamescreens.cardboardadapter.CardboardGame;
import com.tda367.parallax.model.core.Player;


public class CardboardMain extends CardboardGame {

    @Override
    public void create(){
        Player player = new Player();
        DeviceManager.setDevice(this);
        GameStateManager.setCardboardGame(this);
        GameStateManager.setCardboardMenuScreen(player);

    }

    @Override
    public void onDrawEye(Eye paramEye){
        cardboardScreen.onDrawEye(paramEye);
    }

    @Override
    public void onNewFrame(HeadTransform headTransform){
        cardboardScreen.onNewFrame(headTransform);
    }
}
