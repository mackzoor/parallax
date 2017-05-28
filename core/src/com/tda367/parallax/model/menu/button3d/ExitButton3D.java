package com.tda367.parallax.model.menu.button3d;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


public class ExitButton3D extends Button3D {


    public ExitButton3D(Vector3f pos, Quat4f rot) {
        super(pos, rot);
        setCollisionModel("3dModels/quittext/quithitbox.obj");
        addToCollisionManager();
    }

}
