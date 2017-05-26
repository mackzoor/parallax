package com.tda367.parallax.model.core.powerups.arsenal;

import java.util.Random;

/**
 * Factory for creating powerUps.
 */
public class PowerUpFactory {

    private static final Random random = new Random();

    private PowerUpFactory() {

    }

    public static Cannon createCannon() {
        return new Cannon();
    }

    public static Missile createMissile() {
        return new Missile();
    }

    /**
     * @return a random powerUp.
     */
    public static IPowerUp createRandom() {
        final int rand = (int) random.nextFloat() * 2;

        switch (rand) {
            case 0:
                return createCannon();
            case 1:
                return createMissile();
            default:
                return createCannon();
        }

    }
}
