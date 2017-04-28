package com.tda367.parallax.parallaxCore;

import java.util.ArrayList;
import java.util.List;

//TODO change getRenderables() to send back a defencive copy of the renderables list.

/**
 * A singleton bus class that holds the current objects that need to be rendered.
 */
public class RenderManager {
    private List<Renderable> renderables;

    private static RenderManager instance;
    public static RenderManager getInstance(){
        if (instance == null) instance = new RenderManager();
        return instance;
    }

    private RenderManager(){
        renderables = new ArrayList<Renderable>();
    }

    public void addRenderTask(Renderable renderable){
        renderables.add(renderable);
    }
    public void removeRenderTask(Renderable renderable){
        renderables.remove(renderable);
    }

    public List<Renderable> getRenderables(){
        return renderables;
    }

}
