package test.test;

import com.tda367.parallax.parallaxCore.Matrix3D;
import com.tda367.parallax.parallaxCore.Vector3D;
import org.junit.Test;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.junit.Assert.*;

public class Matrix3DTest {

    @Test
    public void rotateX() throws Exception {
        //TODO change order of rows and columns, should transpose
        Vector3D v0 = new Vector3D(1, 0, 0);
        Vector3D v1 = new Vector3D(0, (float) cos(20), 0);
        Vector3D v2 = new Vector3D(0, 0, (float) cos(20));

        Matrix3D matrix2 = new Matrix3D();

        Matrix3D rotatedMatrix = matrix2.rotateX(20);
        Matrix3D resultMatrix = new Matrix3D(v0, v1, v2);
        assertTrue(rotatedMatrix.equals(resultMatrix));
    }

    @Test
    public void subtract() throws Exception {
        Matrix3D matrix = new Matrix3D(1,2,3,1,2,3,1,5,4);

        Matrix3D onesMatrix = new Matrix3D(1,1,1,1,1,1,1,1,1);

        Matrix3D resultMatrix = new Matrix3D(0,1,2,0,1,2,0,4,3);

        assertTrue(matrix.subtract(onesMatrix).equals(resultMatrix));
    }

    @Test
    public void add() throws Exception {
        Matrix3D matrix = new Matrix3D(1,2,3,1,2,3,1,2,3);

        Matrix3D onesMatrix = new Matrix3D(1,1,1,1,1,1,1,1,1);

        Matrix3D resultMatrix = new Matrix3D(2,3,4,2,3,4,2,3,4);

        assertTrue(matrix.add(onesMatrix).equals(resultMatrix));
    }

    @Test
    public void projection() throws Exception {
    }

    @Test
    public void multi() throws Exception {

        Vector3D v0 = new Vector3D(1, 1, 1);
        Vector3D v1 = new Vector3D(2, 2, 2);
        Vector3D v2 = new Vector3D(3, 3, 3);

        Matrix3D matrix1 = new Matrix3D(v0, v1, v2);

        Vector3D v3 = new Vector3D(1, 0, 0);
        Vector3D v4 = new Vector3D(0, 1, 0);
        Vector3D v5 = new Vector3D(1, 1, 1);

        Matrix3D matrix2 = new Matrix3D(v3, v4, v5);

        Vector3D v6 = new Vector3D(2, 2, 1);
        Vector3D v7 = new Vector3D(4, 4, 2);
        Vector3D v8 = new Vector3D(6, 6, 3);


        Matrix3D matrix3 = new Matrix3D(v6, v7, v8);


        //assertTrue(matrix1.multi(matrix1, matrix2).equals(matrix3));


    }

    @Test
    public void equals() {
        Vector3D v0 = new Vector3D(1, 2, 3);
        Vector3D v1 = new Vector3D(1, 2, 3);
        Vector3D v2 = new Vector3D(1, 2, 3);
        Matrix3D matrix = new Matrix3D(v0,v1,v2);

        Vector3D v3 = new Vector3D(1, 0, 3);
        Vector3D v4 = new Vector3D(1, 2, 3);
        Vector3D v5 = new Vector3D(1, 2, 3);
        Matrix3D matrix2 = new Matrix3D(v3,v4,v5);

        assertTrue(matrix.equals(matrix));
        assertTrue((!matrix.equals(matrix2)));

    }
}