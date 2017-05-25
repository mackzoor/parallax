package com.tda367.parallax.controller.gamescreens;

import com.tda367.parallax.model.core.Player;

/**
 * Created by Markus on 2017-05-25.
 */

public interface GameStateChangeListener {
    public void gameStateChanged(GameState nextState, Player player);
}
