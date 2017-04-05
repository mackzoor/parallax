package com.tda367.parallax.parallaxCore;

import com.sun.javafx.geom.transform.BaseTransform;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


/**
 * Self made matrix class. Mostly used to save the rotation of objects.
 */
public class Matrix3D {

    private final Vector3D v0;
    private final Vector3D v1;
    private final Vector3D v2;

    public Matrix3D() {
        v0 = new Vector3D(1, 0, 0);
        v1 = new Vector3D(0, 1, 0);
        v2 = new Vector3D(0, 0, 1);
    }

    public Matrix3D(Vector3D v0, Vector3D v1, Vector3D v2) {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
    }

    public Matrix3D rotateX(float rotation) {
        Vector3D v0 = new Vector3D(1, 0, 0);
        Vector3D v1 = new Vector3D(0, (float) cos(rotation), (float) -sin(rotation));
        Vector3D v2 = new Vector3D(0, (float) sin(rotation), (float) cos(rotation));

        Matrix3D rotationMatrix = new Matrix3D(v0, v1, v2);


        return multi(rotationMatrix, this);


    }

    public void rotateY(Matrix3D matrix, float rotation) {

    }

    public void rotateZ(Matrix3D matrix, float rotation) {

    }

    public void subtract(Matrix3D matrix, Matrix3D otherMatrix) {

    }

    public void add(Matrix3D matrix, Matrix3D otherMatrix) {

    }

    public void projection(Matrix3D matrix, Vector3D line) {

    }

    public Matrix3D multi(Matrix3D matrix, Matrix3D otherMatrix) {
        return null;

    }

    public void setMatrix3D(Vector3D v0, Vector3D v1, Vector3D v2) {
        new Matrix3D(v0, v1, v2);

    }


    public boolean equals(Matrix3D matrix) {
        return this.v0.equals(matrix.v0) && this.v1.equals(matrix.v1) && this.v2.equals(matrix.v2);


        //TODO Rotate Global & Local
        //TODO Other features
    }
}
