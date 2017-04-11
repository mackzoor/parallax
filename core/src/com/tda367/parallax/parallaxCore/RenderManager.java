package com.tda367.parallax.parallaxCore;

import java.util.List;

/**
 * Created by Anthony on 11/04/2017.
 */
public class RenderManager {
    private List<Renderable> renderables;

    private static RenderManager instance;
    RenderManager getInstance(){
        if (instance == null) instance = new RenderManager();
        return instance;
    }

    void addRenderTask(Renderable renderable){
        renderables.add(renderable);
    }
    void removeRenderTask(Renderable renderable){
        renderables.remove(renderable);
    }

    List<Renderable> getRenderables(){
        return renderables;
    }

}
