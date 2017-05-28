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

    public static IPowerUp createRandomPowerUp() {
        final int randomInt = RAND.nextInt(3);
        IPowerUp returnPowerUp;
        switch (randomInt) {
            case 0:
                returnPowerUp = createCannon();
                break;
            case 1:
                returnPowerUp = createMissile();
                break;
            default:
                returnPowerUp = createShield();
                break;
        }
        return returnPowerUp;
    }
}
