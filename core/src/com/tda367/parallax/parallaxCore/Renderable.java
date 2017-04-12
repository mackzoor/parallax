package com.tda367.parallax.parallaxCore;

/**
 * Created by Anthony on 11/04/2017.
 */
public interface Renderable extends IModel, Transformable {

    void addToRenderManager();
    void removeFromRenderManager();

}
