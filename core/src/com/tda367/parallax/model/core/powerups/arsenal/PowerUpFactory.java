package com.tda367.parallax.model.core.powerups.arsenal;

import java.util.Random;

/**
 * Factory for creating powerUps.
 */
public class PowerUpFactory {

    public static Cannon createCannon(){
        return new Cannon();
    }

    public static com.tda367.parallax.model.core.powerups.arsenal.Missile createMissile(){
        return new com.tda367.parallax.model.core.powerups.arsenal.Missile();
    }

    /**
     *
     * @return a random powerUp.
     */
    public static com.tda367.parallax.model.core.powerups.arsenal.IPowerUp createRandom() {
        int random = (int) new Random().nextFloat()*2;

        switch (random) {
            case 0: return createCannon();
            case 1: return createMissile();
            default: return createCannon();
        }

    }
}
