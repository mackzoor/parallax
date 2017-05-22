package com.tda367.parallax.model.parallaxcore.world.courseobstacles;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.Random;

/**
 * Factory for creating obstacles.
 */
public final class ObstacleFactory {

    private ObstacleFactory() {}

    //Boxes
    public static BoxObstacle getBoxObstacleInstance(Vector3f pos, Quat4f rot){
        return new BoxObstacle(pos, rot);
    }
    public static BoxObstacle getBoxObstacleInstance(Vector3f pos){
        return new BoxObstacle(pos, new Quat4f(0,0,0,1));
    }

    //Walls
    public static WallObstacle getTopWallInstance(Vector3f pos){
        return new WallObstacle(pos, new Quat4f(0,0.7071f,0,0.7071f));
    }
    public static WallObstacle getRightWallInstance(Vector3f pos){
        return new WallObstacle(pos, new Quat4f(0,1,0,0));
    }
    public static WallObstacle getBottomWallInstance(Vector3f pos){
        return new WallObstacle(pos, new Quat4f(0,-0.7071f,0,0.7071f));
    }
    public static WallObstacle getLeftWallInstance(Vector3f pos){
        return new WallObstacle(pos, new Quat4f(0,0,0,1));
    }
    public static WallObstacle getRandomWallInstance(Vector3f pos){
        Random rand = new Random();

        int randomInt = (int) (rand.nextFloat() * 4);


        if (randomInt == 0){
            return getTopWallInstance(pos);
        } else if (randomInt == 1) {
            return getRightWallInstance(pos);
        } else if (randomInt == 2) {
            return getBottomWallInstance(pos);
        } else {
            return getLeftWallInstance(pos);
        }
    }
}
