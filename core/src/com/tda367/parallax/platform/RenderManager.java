package com.tda367.parallax.platform;

/**
 * Created by Anthony on 11/04/2017.
 */
public class RenderManager {

    private static RenderManager instance;
    RenderManager getInstance(){
        if (instance == null) instance = new RenderManager();
        return instance;
    }

    RenderManager(){

    }

    void addRenderTask(){

    }


}
