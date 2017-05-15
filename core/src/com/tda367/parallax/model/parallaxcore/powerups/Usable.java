package com.tda367.parallax.model.parallaxcore.powerups;

import com.tda367.parallax.model.parallaxcore.util.Updatable;
import com.tda367.parallax.model.parallaxcore.spacecraft.ISpaceCraft;

/**
 * Items that can be usable by {@link ISpaceCraft}.
 */

public interface Usable extends Updatable {
    void use(/*TODO Add interface for classes that can use Usables*/);
    boolean isDead();
}
