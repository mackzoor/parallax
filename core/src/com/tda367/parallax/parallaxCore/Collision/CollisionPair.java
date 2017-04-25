package com.tda367.parallax.parallaxCore.Collision;

import com.tda367.parallax.parallaxCore.Collidable;

/**
 * Created by amk19 on 24/04/2017.
 */
public class CollisionPair {

    private Collidable coll1;
    private Collidable coll2;

    public CollisionPair(Collidable coll1, Collidable coll2) {
        this.coll1 = coll1;
        this.coll2 = coll2;
    }

    public Collidable getColl1() {
        return coll1;
    }

    public Collidable getColl2() {
        return coll2;
    }
}
