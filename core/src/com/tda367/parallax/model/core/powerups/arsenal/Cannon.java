package com.tda367.parallax.model.core.powerups.arsenal;

import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollidableType;
import com.tda367.parallax.model.core.util.Transformable;
import com.tda367.parallax.utilities.MathUtilities;

import lombok.Getter;
import lombok.Setter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.Random;

/**
 * The cannon PowerUp fires a cannon shot towards the direction it is pointed at.
 */
public class Cannon extends PowerUpBase {
    @Setter @Getter private Vector3f velocity;

    @Getter @Setter private Vector3f enemyTargetPosition;

    private static final float VELOCITY_MULTIPLIER_X = 10f;
    private static final float VELOCITY_MULTIPLIER_Y = 1f;
    private static final float VELOCITY_MULTIPLIER_Z = 10f;

    private final static String COLLISION_MODEL = "3dModels/box/hitbox.obj";

    private int timeAlive;
    private final static int LIFE_LENGTH = 4000;

    Cannon(){
        super();
        timeAlive = 0;
        this.velocity = new Vector3f();
        enemyTargetPosition = new Vector3f();
    }

    private Vector3f quatToDirection(Quat4f q){
        float div = (float) Math.sqrt(q.x * q.x + q.y * q.y + q.z * q.z);
        float x = q.x / div;
        float y = q.y / div;
        float z = q.z / div;

        Vector3f vec = new Vector3f(-z, x,-y);
        vec.normalize();
        return vec;
    }

    //Launches the cannon round.
    @Override
    public void activate(Transformable transformable) {
        Vector3f aimTarget = MathUtilities.rotateVectorByQuat(new Vector3f(0,1,0), transformable.getRot());
        super.activate(transformable);
        super.addToCollisionManager();
        super.enableCollision();

        //TODO Make lazer shoot in the direction that the transformable is rotated.
        //Offset cannon round rotation by 90 degrees due to rotated 3d model.
//        this.rot = new Quat4f(0,0,0.7071f,0.7071f);

        //Rotate the cannon round with the given rotation.
//        this.rot.mul(transformable.getRot());

        //Sets the cannon round starting position to the one given in the arguments.
//        this.pos = new Vector3f(transformable.getPos());

//        velocity = quatToDirection(transformable.getRot());


        //Placeholder
        super.setPos(new Vector3f(transformable.getPos()));
        super.getPos().add(new Vector3f(0,1,0));
        velocity = MathUtilities.rotateVectorByQuat(new Vector3f(0,1,0), transformable.getRot());



        velocity.scale(30);

        playCannonSound();
    }
    @Override
    public void use() {
        //I can't remember what this was for....
    }


    //Updates the cannon.
    @Override
    public void update(int milliSinceLastUpdate){
        if (super.isActive()){
            timeAlive += milliSinceLastUpdate;
            updatePosition(milliSinceLastUpdate);
        }
        if (timeAlive > LIFE_LENGTH){
            die();
        }
    }
    private void die() {
        super.disableCollision();
        super.removeFromCollisionManager();
        super.setActive(false);
        super.setDead(true);
    }
    private void updatePosition(int milliSinceLastUpdate){
        super.getPos().add(new Vector3f(
                velocity.getX() * ((float) milliSinceLastUpdate/1000),
                velocity.getY() * ((float) milliSinceLastUpdate/1000),
                velocity.getZ() * ((float) milliSinceLastUpdate/1000)
        ));
        Vector3f directionalVector = generateDirectionVector(getEnemyTargetPosition());
        moveOnDirectionVector(directionalVector, milliSinceLastUpdate);
        this.setRot(MathUtilities.vectorToQuat(directionalVector));
    }
    private void playCannonSound(){
        Random rand = new Random();
        int randomSong = rand.nextInt(200 - 1 + 1) + 1;

        //Plays a funny sound every 200 shots
        if(randomSong > 199){
            AudioQueue.getInstance().playSound("cannonLow.mp3","sounds/effects", 0.3f);
        } else {
            AudioQueue.getInstance().playSound("cannon.mp3","sounds/effects", 0.8f);
        }
    }

    //Collision
    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL;
    }
    @Override
    public CollidableType getCollidableType() {
        return CollidableType.HARMFUL;
    }
    @Override
    public void handleCollision(Collidable collidable) {
        if (timeAlive > 250 && CollidableType.OBSTACLE == collidable.getCollidableType()) {
            System.out.println("cannon");
            die();
        }
    }

    private Vector3f generateDirectionVector(Vector3f target){
        //Generate a direction vector based on the target position subtracted with the position of the ship.
        Vector3f returnVector = new Vector3f(target.getX()-getPos().getX(), target.getY()-getPos().getY(), target.getZ()-getPos().getZ());

        //Normalize the directional vector before sending it away.
        returnVector.normalize();

        //Return a new vector with the current direction vector
        return returnVector;
    }

    private void moveOnDirectionVector(Vector3f directionalVector, int milliSinceLastUpdate){
        getPos().add(new Vector3f((directionalVector.getX()*(float)milliSinceLastUpdate/1000)* VELOCITY_MULTIPLIER_X,
                (directionalVector.getY()*(float)milliSinceLastUpdate/1000)* VELOCITY_MULTIPLIER_Y,
                (directionalVector.getZ()*(float)milliSinceLastUpdate/1000)* VELOCITY_MULTIPLIER_Z));
    }
}

