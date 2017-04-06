package com.tda367.parallax.oldMathClasses;

/**
 * Self made matrix class. Mostly used to save the rotation of objects.
 */
public class Matrix3D {

    private final Vector3D v0;
    private final Vector3D v1;
    private final Vector3D v2;

    Matrix3D(){
        v0 = new Vector3D(1,0,0);
        v1 = new Vector3D(0,1,0);
        v2 = new Vector3D(0,0,1);
    }



    //TODO Rotate Global & Local
    //TODO Other features
}
