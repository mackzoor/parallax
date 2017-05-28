package com.tda367.parallax.utilities;

import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Utility class for math functions.
 */
public final class MathUtilities {

    private MathUtilities() {
    }

    public static Quat4f eulerToQuaternion(double pitch, double roll, double yaw) {
        final Quat4f q = new Quat4f();

        final float t0 = (float) cos(yaw * 0.5);
        final float t1 = (float) sin(yaw * 0.5);
        final float t2 = (float) cos(roll * 0.5);
        final float t3 = (float) sin(roll * 0.5);
        final float t4 = (float) cos(pitch * 0.5);
        final float t5 = (float) sin(pitch * 0.5);

        q.setW(t0 * t2 * t4 + t1 * t3 * t5);
        q.setX(t0 * t3 * t4 - t1 * t2 * t5);
        q.setY(t0 * t2 * t5 + t1 * t3 * t4);
        q.setZ(t1 * t2 * t4 - t0 * t3 * t5);

        q.normalize();
        return q;
    }

    public static Vector3f eulerToVector(float pitch, float roll, float yaw) {
        final float x = (float) (-cos(yaw) * sin(pitch) * sin(roll) - sin(yaw) * cos(roll));
        final float y = (float) (-sin(yaw) * sin(pitch) * sin(roll) + cos(yaw) * cos(roll));
        final float z = (float) (cos(pitch) * sin(roll));

        return new Vector3f(x, y, z);
    }

    public static Quat4f vectorToQuat(Vector3f v) {
        final Vector3f targetDirection = new Vector3f(v);

        final Vector3f zFlat = new Vector3f(targetDirection);
        zFlat.setZ(0);
        float zRotation = zFlat.angle(new Vector3f(0, 1, 0));

        final Vector3f heightDifference = new Vector3f(targetDirection);
        float xRotation = heightDifference.angle(new Vector3f(zFlat));

        if (targetDirection.getX() > 0) {
            zRotation *= -1;
        }
        if (targetDirection.getZ() < 0) {
            xRotation *= -1;
        }

        return MathUtilities.eulerToQuaternion(0, xRotation, zRotation);
    }

    public static Vector3f rotateVectorByQuat(Vector3f vector, Quat4f quat) {

        final Matrix3f rotationMatrix = new Matrix3f();

        final float q0 = quat.getW();
        final float q1 = quat.getX();
        final float q2 = quat.getY();
        final float q3 = quat.getZ();

        rotationMatrix.m00 = -1 * (2f * q2 * q2 + 2f * q3 * q3 - 1f);
        rotationMatrix.m01 = -1 * (-2f * (q1 * q2 + q0 * q3));
        rotationMatrix.m02 = -1 * (-2f * (q1 * q3 - q0 * q2));

        rotationMatrix.m10 = 2f * (q1 * q2 - q0 * q3);
        rotationMatrix.m11 = 1f - 2f * q1 * q1 - 2f * q3 * q3;
        rotationMatrix.m12 = 2f * (q2 * q3 + q0 * q1);

        rotationMatrix.m20 = -1 * (-2f * (q1 * q3 + q0 * q2));
        rotationMatrix.m21 = -1 * (-2f * (q2 * q3 - q0 * q1));
        rotationMatrix.m22 = -1 * (2f * q1 * q1 + 2f * q2 * q2 - 1f);

        rotationMatrix.transform(vector);

        return vector;
    }
}
