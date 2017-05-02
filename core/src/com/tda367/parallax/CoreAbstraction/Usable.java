package com.tda367.parallax.CoreAbstraction;

import com.tda367.parallax.parallaxCore.spaceCraft.ISpaceCraft;

/**
 * Items that can be usable by {@link ISpaceCraft}.
 */
public interface Usable {
    void activate(/*TODO Add interface for classes that can use Usables*/);
    void update(int milliSinceLastUpdate);
}
