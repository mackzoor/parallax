package com.tda367.parallax.model.core.collision;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton bus class that holds the current objects that need to be checked for collision.
 */

public final class CollisionManager {
    @Getter private List<com.tda367.parallax.model.core.collision.Collidable> collidables;
    private List<ICollisionCalculator> calculators;
    @Getter private List<com.tda367.parallax.model.core.collision.CollisionObserver> observers;

    private static CollisionManager instance;
    public static CollisionManager getInstance(){
        if (instance == null) {
            instance = new CollisionManager();
        }
        return instance;
    }

    private CollisionManager(){
        collidables = new ArrayList<com.tda367.parallax.model.core.collision.Collidable>();
        calculators = new ArrayList<ICollisionCalculator>();
        observers = new ArrayList<com.tda367.parallax.model.core.collision.CollisionObserver>();
    }

    public void addCollisionCheck(com.tda367.parallax.model.core.collision.Collidable collidable){
        collidables.add(collidable);
    }
    public void removeCollisionCheck(com.tda367.parallax.model.core.collision.Collidable collidable){
        collidables.remove(collidable);
    }

    public void subscribeToCollisions(com.tda367.parallax.model.core.collision.CollisionObserver observer){
        observers.add(observer);
    }
    public void unnubscribeToCollisions(com.tda367.parallax.model.core.collision.CollisionObserver observer){
        observers.remove(observer);
    }

    public void alertObservers(com.tda367.parallax.model.core.collision.CollisionPair collisionPair) {
        for (com.tda367.parallax.model.core.collision.CollisionObserver observer : observers) {
            observer.respondToCollision(collisionPair);
        }
    }
}
