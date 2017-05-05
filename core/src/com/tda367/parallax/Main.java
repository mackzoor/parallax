package com.tda367.parallax;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tda367.parallax.controller.gamestates.GameStateManager;

/**
 * Created by Markus on 2017-05-04.
 */

public class Main extends Game {

    @Override
    public void create() {
        GameStateManager.setMainMenuScreen(this);
    }

    @Override
    public void render() {
        screen.render(Gdx.graphics.getDeltaTime());
    }
}
