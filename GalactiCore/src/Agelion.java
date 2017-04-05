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
    }
    Agelion(){
        this.pos = new Vector3D();
        this.rot = new Matrix3D();
        this.velocity = 1;
    }


    //  Speed   //
    public void speedTarget(float speed){
        //TODO implement speedTarget
    }
    public void accelerateTarget(float accelerate){
        //TODO implement accelerateTarget
    }

    // Pan Y&X  //
    public void panXTarget(){
        //TODO implement panXTarget
    }
    public void panYTarget(){
        //TODO panYTarget
    }

    public void panXAcceleration(){
        //TODO implement panXAcceleration
    }
    public void panYAcceleration(){
        //TODO panYAcceleration
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
    public void update() {
        //TODO Update ship position. Depends on orientation and speed.
    }
}

