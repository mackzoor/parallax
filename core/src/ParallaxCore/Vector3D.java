package ParallaxCore;

/**
 * A 3d vector class that handles vectors in a three dimensional space.
 */
public class Vector3D {

    /*Floats are used instead of doubles because graphics cards
     are more optimized for floating point operations.*/

    private final float x;
    private final float y;
    private final float z;


    public Vector3D(){
        x = 0;
        y = 0;
        z = 0;
    }
    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public Vector3D add(Vector3D v){
        Vector3D vectorNew = new Vector3D(
                this.x + v.getX(),
                this.y + v.getY(),
                this.z + v.getZ()
        );
        return vectorNew;
    }
    public Vector3D add(float x, float y, float z){
        Vector3D vectorNew = new Vector3D(
                this.x + x,
                this.y + y,
                this.z + z
        );
        return vectorNew;
    }

    public Vector3D sub(Vector3D v){
        Vector3D vectorNew = new Vector3D(
                this.x - v.getX(),
                this.y - v.getY(),
                this.z - v.getZ()
        );
        return vectorNew;
    }
    public Vector3D sub(float x, float y, float z){
        Vector3D vectorNew = new Vector3D(
                this.x - x,
                this.y - y,
                this.z - z
        );
        return vectorNew;
    }

    public Vector3D rotateX(float degree){
        //TODO Implement.
        return null;
    }
    public Vector3D rotateY(float degree){
        //TODO Implement.
        return null;
    }
    public Vector3D rotateZ(float degree){
        //TODO Implement.
        return null;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getZ() {
        return z;
    }
}