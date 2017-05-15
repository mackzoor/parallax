package com.tda367.parallax.view.util;

import com.tda367.parallax.model.parallaxcore.util.Transformable;

/**
 * Interface for classes that are renderable.
 */

public interface Renderable extends Transformable {
    void addToRenderManager();
    void removeFromRenderManager();
    Model getModel();
}
