package com.tda367.parallax.utilities;

import org.junit.Test;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import static org.junit.Assert.*;


public class MathUtilitiesTest {

    @Test
    public void eulerToQuaternion() throws Exception {
        Quat4f quat4f;
        quat4f = MathUtilities.eulerToQuaternion(0,0,Math.PI);
        assertTrue(quat4f.getZ() == 1);

    }

    @Test
    public void eulerToVector() throws Exception {
        Vector3f vector3f;
        vector3f = MathUtilities.eulerToVector(0,0,(float) Math.PI);
        assertTrue(vector3f.y == -1);


    }

    @Test
    public void vectorToQuat() throws Exception {
        Vector3f vector3f = new Vector3f(0,0,1);
        Quat4f quat4f = MathUtilities.vectorToQuat(vector3f);
        System.out.println(quat4f);

    }

    @Test
    public void rotateVectorByQuat() throws Exception {
        Vector3f vector3f = new Vector3f(3,3,3);
        Quat4f quat4f = new Quat4f(0,0,-1,0);
        MathUtilities.rotateVectorByQuat(vector3f,quat4f);
        assertTrue(vector3f.y == -3 && vector3f.x == -3 && vector3f.z == 3);
    }

}