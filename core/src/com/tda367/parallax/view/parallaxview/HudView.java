package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.Player;

/**
 * View class for rendering a {@link Player} hud.
 */
public class HudView implements View{

    private final Player player;

    /**
     * Creates a HudView from a {@link Player}.
     * @param player to be used to create the PlayerView.
     */
    HudView(Player player) {
        this.player = player;
    }

    @Override
    public void render() {

    }
    @Override
    public boolean isObsolete() {
        return false;
    }
}
