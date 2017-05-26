package com.tda367.parallax.model.core.collision;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton bus class that holds the current objects that need to be checked for collision.
 */

public final class CollisionManager {
    @Getter
    private List<Collidable> collidables;
    private List<ICollisionCalculator> calculators;
    @Getter
    private List<CollisionObserver> observers;

    private static CollisionManager instance;

    public static CollisionManager getInstance() {
        if (instance == null) {
            instance = new CollisionManager();
        }
        return instance;
    }

    private CollisionManager() {
        this.collidables = new ArrayList<Collidable>();
        this.calculators = new ArrayList<ICollisionCalculator>();
        this.observers = new ArrayList<CollisionObserver>();
    }

    public void addCollisionCheck(Collidable collidable) {
        this.collidables.add(collidable);
    }

    public void removeCollisionCheck(Collidable collidable) {
        this.collidables.remove(collidable);
    }

    public void subscribeToCollisions(CollisionObserver observer) {
        this.observers.add(observer);
    }

    public void unnubscribeToCollisions(CollisionObserver observer) {
        this.observers.remove(observer);
    }

    public void alertObservers(CollisionResult collisionResult) {
        for (final CollisionObserver observer : this.observers) {
            observer.respondToCollision(collisionResult);
        }
    }
}
