package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.coreabstraction.Usable;

/**
 * ExtraLife PowerUp increments the life of the Agelion ship
 */

public class ExtraLife implements Usable {


    private boolean isDead = false;

    @Override
    public void use() {

    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void update(int milliSinceLastUpdate) {

    }
}