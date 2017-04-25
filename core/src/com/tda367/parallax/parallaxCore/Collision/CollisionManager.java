package com.tda367.parallax.parallaxCore.Collision;

import com.tda367.parallax.parallaxCore.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amk19 on 12/04/2017.
 */
public class CollisionManager {
    private List<Collidable> collidables;

    private static CollisionManager instance;
    public static CollisionManager getInstance(){
        if (instance == null) instance = new CollisionManager();
        return instance;
    }


    private CollisionManager(){
        collidables = new ArrayList<Collidable>();
    }

    public void addRenderTask(Collidable collidable){
        collidables.add(collidable);
    }
    public void removeRenderTask(Collidable collidable){
        collidables.remove(collidable);
    }

    public List<Collidable> getCollidables(){
        return collidables;
    }
}
