package com.tda367.parallax.model.menu.button3d;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;


public class StartButton3D extends AbstractButton3D {


    public StartButton3D(Vector3f pos, Quat4f rot) {
        super(pos, rot);
        setCollisionModel("3dModels/playtext/playhitbox.obj");
        addToCollisionManager();
    }

}
