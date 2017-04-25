package com.tda367.parallax.parallaxCore.Collision;

import com.tda367.parallax.parallaxCore.Collidable;

import java.util.List;

/**
 * Created by amk19 on 12/04/2017.
 */
public interface ICollisionCalculator {

    boolean hasCollided(Collidable first, Collidable second);
    List<CollisionPair> getCollisions(List<? extends Collidable> collidables);
    List<CollisionPair> getCollisions(List<? extends Collidable> firstGroup,List<? extends Collidable> secondGroup);

}
