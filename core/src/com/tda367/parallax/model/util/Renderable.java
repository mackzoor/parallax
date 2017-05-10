package com.tda367.parallax.model.util;

/**
 * Interface for classes that are renderable.
 */

public interface Renderable extends Transformable {
    void addToRenderManager();
    void removeFromRenderManager();
    Model getModel();
}
