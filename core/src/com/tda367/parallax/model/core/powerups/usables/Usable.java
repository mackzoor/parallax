package com.tda367.parallax.model.core.powerups.usables;

import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.model.core.util.Updatable;

/**
 * Items that can be usable by {@link ISpaceCraft}.
 */

public interface Usable extends Updatable {
    void use(/*TODO Add interface for classes that can use Usables*/);
    boolean isDead();
}
