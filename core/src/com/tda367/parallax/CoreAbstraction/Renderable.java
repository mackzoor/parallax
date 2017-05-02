package com.tda367.parallax.CoreAbstraction;

import com.tda367.parallax.CoreAbstraction.IModel;
import com.tda367.parallax.CoreAbstraction.Transformable;

/**
 * Interface for classes that are renderable.
 */
public interface Renderable extends IModel, Transformable {
    void addToRenderManager();
    void removeFromRenderManager();
}
