package com.tda367.parallax.parallaxCore.Collision;

import com.tda367.parallax.CoreAbstraction.Collidable;
import java.util.ArrayList;
import java.util.List;

/**
 * A singleton bus class that holds the current objects that need to be checked for collision.
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

    public void addCollisionCheck(Collidable collidable){
        collidables.add(collidable);
    }
    public void removeCollisionCheck(Collidable collidable){
        collidables.remove(collidable);
    }

    public List<Collidable> getCollidables(){
        return collidables;
    }
}
