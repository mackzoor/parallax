package com.tda367.parallax.model.core.powerups.usables;

/**
 * ExtraLife PowerUp increments the life of the Agelion ship
 */

public class ExtraLife implements com.tda367.parallax.model.core.powerups.usables.Usable {


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