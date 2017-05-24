package com.tda367.parallax.model.core.collision;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton bus class that holds the current objects that need to be checked for collision.
 */

public final class CollisionManager {
    @Getter private List<Collidable> collidables;
    private List<ICollisionCalculator> calculators;
    @Getter private List<CollisionObserver> observers;

    private static CollisionManager instance;
    public static CollisionManager getInstance(){
        if (instance == null) {
            instance = new CollisionManager();
        }
        return instance;
    }

    private CollisionManager(){
        collidables = new ArrayList<Collidable>();
        calculators = new ArrayList<ICollisionCalculator>();
        observers = new ArrayList<CollisionObserver>();
    }

    public void addCollisionCheck(Collidable collidable){
        collidables.add(collidable);
    }
    public void removeCollisionCheck(Collidable collidable){
        collidables.remove(collidable);
    }

    public void subscribeToCollisions(CollisionObserver observer){
        observers.add(observer);
    }
    public void unnubscribeToCollisions(CollisionObserver observer){
        observers.remove(observer);
    }

    public void alertObservers(CollisionResult collisionResult) {
        for (CollisionObserver observer : observers) {
            observer.respondToCollision(collisionResult);
        }
    }
}
