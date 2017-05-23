package com.tda367.parallax.model.core.util;

/**
 * Interface for objects that need to be updated every game tick/loop.
 */

public interface Updatable {
    void update(int milliSinceLastUpdate);
}
