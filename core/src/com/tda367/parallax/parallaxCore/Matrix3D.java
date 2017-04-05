package com.tda367.parallax.parallaxCore;

import com.sun.javafx.geom.transform.BaseTransform;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public Matrix3D(float a11,float a21, float a31, float a12, float a22, float a32, float a13,float a23, float a33){
        this.v0 = new Vector3D(a11,a21,a31);
        this.v1 = new Vector3D(a12,a22,a32);
        this.v2 = new Vector3D(a13,a23,a33);
    }


    public Matrix3D rotateX(float rotation) {
        Vector3D v0 = new Vector3D(1, 0, 0);
        Vector3D v1 = new Vector3D(0, (float) cos(rotation), (float) -sin(rotation));
        Vector3D v2 = new Vector3D(0, (float) sin(rotation), (float) cos(rotation));

        Matrix3D rotationMatrix = new Matrix3D(v0, v1, v2);


        return null; //multi(rotationMatrix, this);


    }

    public void rotateY(Matrix3D matrix, float rotation) {

    }

    public void rotateZ(Matrix3D matrix, float rotation) {

    }

    public void subtract(Matrix3D matrix, Matrix3D otherMatrix) {

    }

    public Matrix3D add(Matrix3D matrix) {
        Vector3D v0 = new Vector3D(matrix.v0.getX() + this.v0.getX(), matrix.v0.getY() + this.v0.getY(),matrix.v0.getZ() + this.v0.getZ());
        Vector3D v1 = new Vector3D(matrix.v1.getX() + this.v1.getX(), matrix.v1.getY() + this.v1.getY(),matrix.v1.getZ() + this.v1.getZ());
        Vector3D v2 = new Vector3D(matrix.v2.getX() + this.v2.getX(), matrix.v2.getY() + this.v2.getY(),matrix.v2.getZ() + this.v2.getZ());
        return new Matrix3D(v0,v1,v2);

    }

    public void projection(Matrix3D matrix, Vector3D line) {

    }

    public Matrix3D multi(Matrix3D matrix) {
        //this.v0.getX() * matrix.v0.getX() + this.v1.getX() * matrix.v0.getY() + this.v2.getX() * matrix.v0.getZ()


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
