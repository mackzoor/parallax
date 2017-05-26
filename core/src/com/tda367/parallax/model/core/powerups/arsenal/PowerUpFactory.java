package com.tda367.parallax.model.core.powerups.arsenal;

import java.util.Random;

/**
 * Factory for creating powerUps.
 */
public class PowerUpFactory {

    private static final Random RAND = new Random();

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
        final int randomNumber = (int) (RAND.nextFloat() * 2);

        if (randomNumber == 0) {
            return createCannon();
        } else if (randomNumber == 1) {
            return createMissile();
        } else {
            return createMissile();
        }
    }
}
