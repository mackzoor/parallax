package com.tda367.parallax.model.coreabstraction;

/**
 * Interface for classes that are renderable.
 */
public interface Renderable extends IModel, Transformable {
    void addToRenderManager();
    void removeFromRenderManager();
}
