package com.tda367.parallax.model.coreabstraction;

import com.tda367.parallax.model.util.Renderable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//TODO change getRenderables() to send back a defencive copy of the renderables list.

/**
 * A singleton bus class that holds the current objects that need to be rendered.
 */

public class RenderQueue {
    @Getter private List<Renderable> renderables;

    @Getter @Setter private float camXCoord;
    @Getter @Setter private float camYCoord;
    @Getter @Setter private float camZCoord;

    @Getter @Setter private float camXQuat;
    @Getter @Setter private float camYQuat;
    @Getter @Setter private float camZQuat;
    @Getter @Setter private float camWQuat;

    private static RenderQueue instance;

    public static RenderQueue getInstance(){
        if (instance == null){
            instance = new RenderQueue();
        }
        return instance;
    }

    private RenderQueue(){
        renderables = new ArrayList<Renderable>();

        camXCoord = 0;
        camYCoord = 0;
        camZCoord = 0;

        camXQuat = 0;
        camYQuat = 0;
        camZQuat = 0;
        camWQuat = 0;
    }

    public void addRenderTask(Renderable renderable){
        renderables.add(renderable);
    }
    public void removeRenderTask(Renderable renderable){
        renderables.remove(renderable);
    }
}
