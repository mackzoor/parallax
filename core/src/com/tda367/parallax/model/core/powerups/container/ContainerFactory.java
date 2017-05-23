package com.tda367.parallax.model.core.powerups.container;

import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.powerups.arsenal.PowerUpFactory;

/**
 * Factory for creating powerUp containers.
 */
public class ContainerFactory {

    /**
     * Creates a {@link Container} with the specified {@link IPowerUp}.
     * @param powerUp used to create Container.
     * @return created Container containing the specified powerUp.
     */
    public static com.tda367.parallax.model.core.powerups.container.IContainer createContainer(IPowerUp powerUp){
        return new Container(powerUp);
    }

    /**
     * Creates a new {@link Container} containing a random {@link IPowerUp}.
     * @return the created Container with random powerUp.
     */
    public static com.tda367.parallax.model.core.powerups.container.IContainer createRandomContainer() {
        IPowerUp powerUp = PowerUpFactory.createRandom();
        return createContainer(powerUp);
    }

}