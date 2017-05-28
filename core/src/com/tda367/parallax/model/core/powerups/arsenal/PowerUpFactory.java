package com.tda367.parallax.model.core.powerups.arsenal;

import java.util.Random;

/**
 * Factory for creating powerUps.
 */
public final class PowerUpFactory {

    private static final Random RAND = new Random();

    private PowerUpFactory() {

    }

    public static Cannon createCannon() {
        return new Cannon();
    }

    public static Missile createMissile() {
        return new Missile();
    }

    public static Shield createShield() {
        return new Shield();
    }

    /**
     * @return a random powerUp.
     */
    public static IPowerUp createRandom() {
        final int randomNumber = (int) (RAND.nextFloat() * 3);

        if (randomNumber == 0) {
            return createCannon();
        } else if (randomNumber == 1) {
            return createMissile();
        } else if (randomNumber == 2) {
            return createShield();
        } else {
            return createMissile();
        }
    }
}
