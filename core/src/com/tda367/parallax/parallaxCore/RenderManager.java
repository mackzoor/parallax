package com.tda367.parallax.parallaxCore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 11/04/2017.
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
