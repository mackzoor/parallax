package com.tda367.parallax.utilities;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Utility class for math functions
 */
public final class MathUtilities {

    private MathUtilities() {}

    public static Quat4f eulerToQuaternion(double pitch, double roll, double yaw) {
        Quat4f q = new Quat4f();

        float t0 = (float) cos(yaw * 0.5);
        float t1 = (float) sin(yaw * 0.5);
        float t2 = (float) cos(roll * 0.5);
        float t3 = (float) sin(roll * 0.5);
        float t4 = (float) cos(pitch * 0.5);
        float t5 = (float) sin(pitch * 0.5);

        q.setW(t0 * t2 * t4 + t1 * t3 * t5);
        q.setX(t0 * t3 * t4 - t1 * t2 * t5);
        q.setY(t0 * t2 * t5 + t1 * t3 * t4);
        q.setZ(t1 * t2 * t4 - t0 * t3 * t5);
        q.normalize();
        return q;
    }

    private static Vector3f quatToDirection(Quat4f q){
        float div = (float) Math.sqrt(q.x * q.x + q.y * q.y + q.z * q.z);
        float x = q.x / div;
        float y = q.y / div;
        float z = q.z / div;

        Vector3f vec = new Vector3f(-z, x,-y);
        vec.normalize();
        return vec;
    }

    public static Vector3f eulerToVector(float pitch, float roll, float yaw){
        float x = (float) (-cos(yaw) * sin(pitch) * sin(roll) -sin(yaw) * cos(roll));
        float y = (float) (-sin(yaw) * sin(pitch) * sin(roll)+cos(yaw) * cos(roll));
        float z = (float) (cos(pitch) * sin(roll));

        return new Vector3f(x,y,z);
    }

    public static Vector3f quatToVector(Quat4f q) {
        /*float div = (float) Math.sqrt(q.x * q.x + q.y * q.y + q.z * q.z);
        float x = q.x / div;
        float y = q.y / div;
        float z = q.z / div;

        return eulerToVector(0,y,z);*/
        Matrix4f m4 = new Matrix4f();
        m4.set(q);

        Matrix3f m3 = new Matrix3f();
        m4.get(m3);
        Vector3f direction = new Vector3f();
        m3.getColumn(2, direction);

        return direction;
    }

    public static Quat4f vectorToQuat(Vector3f v) {
        Vector3f targetDirection = new Vector3f(v);

        Vector3f zFlat = new Vector3f(targetDirection);
        zFlat.setZ(0);
        float zRotation = zFlat.angle(new Vector3f(0, 1, 0));

        Vector3f heightDifference = new Vector3f(targetDirection);
        float xRotation = heightDifference.angle(new Vector3f(zFlat));

        if (targetDirection.getX() > 0) {
            zRotation *= -1;
        }
        if (targetDirection.getZ() < 0) {
            xRotation *= -1;
        }

        return MathUtilities.eulerToQuaternion(0, xRotation, zRotation);
    }


}
