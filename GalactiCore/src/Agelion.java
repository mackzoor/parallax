/**
 * Represents the spacecraft in our game.
 */
public class Agelion implements SpaceCraft{

    private int health; //Current health
    private PowerUp pu; //Current stored power up

    private float velocity;

    private Vector3D pos; //Position of the craft
    private Matrix3D rot; //Rotation of the craft


    Agelion(Vector3D position, Matrix3D rotation, float startVelocity){
        this.pos = position;
        this.rot = rotation;
        this.velocity = startVelocity;
        this.health = 5;
        this.pu = null;
    }
    Agelion(){
        new Agelion(new Vector3D(), new Matrix3D(), 1);
    }


    //  Speed   //
    public void setSpeedTarget(float speed){
        //TODO implement setSpeedTarget
    }
    public void setAccelerateTarget(float accelerate){
        //TODO implement setAccelerateTarget
    }

    // Pan Y&X  //
    public void setPanXTarget(float xTarget){
        //TODO implement setPanXTarget
    }
    public void setPanYTarget(float yTarget){
        //TODO setPanYTarget
    }

    public void setPanXAcceleration(float xAcceleration){
        //TODO implement setPanXAcceleration
    }
    public void setPanYAcceleration(float yAcceleration){
        //TODO setPanYAcceleration
    }

    // Action   //
    public void action(){
        pu.usePU(pos, rot);
    }


    //TODO some sort of rotation engine?
    //TODO Spacecraft flight system. (Acc pan etc)


    //TODO Geometry?
    //TODO Turn left right up down? Set speed of movement or set target placement target?
    //TODO Accelerate decelerate? Set thrust target or set speed target?
    //TODO More?


    @Override
    public Vector3D getPos() {
        return pos;
    }
    @Override
    public Matrix3D getRot() {
        return rot;
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        //TODO Update ship position. Depends on orientation and speed.
    }
}

