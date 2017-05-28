package com.tda367.parallax.model.core.powerups.usables;

import lombok.Setter;

/**
 * ExtraLife PowerUp increments the life of the Agelion ship.
 */

public class ExtraLife implements com.tda367.parallax.model.core.powerups.usables.Usable {


    @Setter
    private boolean dead;

    ExtraLife() {
        this.dead = false;
    }

    @Override
    public void use() {

    }

    @Override
    public boolean isDead() {
        return this.dead;
    }

    @Override
    public void update(int milliSinceLastUpdate) {
    }
}
