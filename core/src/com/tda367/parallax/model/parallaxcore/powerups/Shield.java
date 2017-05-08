package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.coreabstraction.Usable;

/**
 * Gives the player a shield that last for several seconds.
 */

public class Shield implements Usable {

    private boolean isDead;

    @Override
    public void use() {}

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void update(int milliSinceLastUpdate) {}
}
