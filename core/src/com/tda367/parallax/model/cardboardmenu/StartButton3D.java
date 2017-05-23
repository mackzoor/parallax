package com.tda367.parallax.model.cardboardmenu;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


public class StartButton3D extends Button3D {


    StartButton3D(Vector3f pos, Quat4f rot) {
        super(pos, rot);
        collisionModel = "3dModels/box/hitbox.obj";
        addToCollisionManager();
    }

}