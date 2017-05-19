package com.tda367.parallax.model.cardboardmenu;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.collision.CollidableType;
import com.tda367.parallax.model.parallaxcore.collision.CollisionManager;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


public class StartButton3D extends Button3D {


    StartButton3D(Vector3f pos, Quat4f rot) {
        super(pos, rot);
        collisionModel = "3dModels/boxObstacle/boxObstacle.g3db";
    }

}