package com.tda367.parallax.parallaxCore;

/**
 * Interface for classes that are renderable.
 */
public interface Renderable extends IModel, Transformable {
    void addToRenderManager();
    void removeFromRenderManager();
}
