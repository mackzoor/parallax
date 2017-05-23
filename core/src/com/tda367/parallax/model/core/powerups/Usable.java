package com.tda367.parallax.model.core.powerups;

import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;

/**
 * Items that can be usable by {@link ISpaceCraft}.
 */

public interface Usable extends com.tda367.parallax.model.core.util.Updatable {
    void use(/*TODO Add interface for classes that can use Usables*/);
    boolean isDead();
}
