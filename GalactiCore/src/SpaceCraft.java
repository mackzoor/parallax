/**
 * Interface for all spaceCraft in the game.
 */
public interface SpaceCraft extends Collidable, Updatable {

    //  Speed   //
    void setSpeedTarget(float speed);
    void setAccelerateTarget(float accelerate);

    // Pan Y&X //
    void setPanXTarget(float xTarget);
    void setPanZTarget(float yTarget);

    void setPanXAcceleration(float xAcceleration);
    void setPanYAcceleration(float yAcceleration);

    //  Action  //
    public void action();
}


