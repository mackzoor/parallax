package com.tda367.parallax.model.coreabstraction;

/**
 * Interface for objects that need to be updated every game tick/loop.
 */

public interface Updatable {
    void update(int milliSinceLastUpdate);
}
