/**
 * Interface for all spaceCraft in the game.
 */
public interface SpaceCraft extends Collidable, Updatable {

    //  Speed   //
    void speedTarget(float speed);
    void accelerateTarget(float accelerate);

    // Pan Y&X //
    void panXTarget();
    void panYTarget();

    void panXAcceleration();
    void panYAcceleration();

    //  Action  //
    public void action();
}


