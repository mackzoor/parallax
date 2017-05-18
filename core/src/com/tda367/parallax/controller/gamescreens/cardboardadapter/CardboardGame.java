package com.tda367.parallax.controller.gamescreens.cardboardadapter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.CardBoardApplicationListener;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;


public abstract class CardboardGame extends Game implements CardBoardApplicationListener {

    protected CardboardScreen cardboardScreen;

    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {

    }

    @Override
    public void onDrawEye(Eye paramEye) {

    }

    @Override
    public void onFinishFrame(Viewport paramViewport) {

    }

    @Override
    public void onRendererShutdown() {

    }

    @Override
    public void onCardboardTrigger() {

    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public void setCardboardScreen(CardboardScreen screen){
        if (this.cardboardScreen != null) this.cardboardScreen.hide();
        this.cardboardScreen = screen;
        if (this.cardboardScreen != null) {
            this.cardboardScreen.show();
            this.cardboardScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }
}
