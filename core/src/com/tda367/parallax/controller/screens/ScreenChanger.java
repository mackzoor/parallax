package com.tda367.parallax.controller.screens;

import com.tda367.parallax.model.core.Player;

public interface ScreenChanger {
    void requestScreenChange(ScreenState nextState, Player player);
}
