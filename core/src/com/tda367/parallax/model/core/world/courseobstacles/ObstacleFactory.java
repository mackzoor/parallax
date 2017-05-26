package com.tda367.parallax.model.core.world.courseobstacles;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.Random;

/**
 * Factory for creating obstacles.
 */
public final class ObstacleFactory {

    private ObstacleFactory() {
    }

    private static Random random = new Random();

    //Boxes
    public static BoxObstacle getBoxObstacleInstance(Vector3f pos, Quat4f rot) {
        return new BoxObstacle(pos, rot);
    }

    public static BoxObstacle getBoxObstacleInstance(Vector3f pos) {
        return new BoxObstacle(pos, new Quat4f(0, 0, 0, 1));
    }

    public static MovingBoxObstacle getMovingBoxInstance(Vector3f pos, boolean random) {
        return new MovingBoxObstacle(
                pos,
                new Quat4f(0, 0, 0, 1),
                true
        );
    }

    //Walls
    private static WallObstacle getTopWallInstance(Vector3f pos) {
        return new WallObstacle(pos, new Quat4f(0, 0.7071f, 0, 0.7071f));
    }

    private static WallObstacle crazyWall(Vector3f pos) {
        return new WallObstacle(pos, new Quat4f(0, random.nextFloat(), 0, random.nextFloat()));
    }

    private static WallObstacle getRightWallInstance(Vector3f pos) {
        return new WallObstacle(pos, new Quat4f(0, 1, 0, 0));
    }

    private static WallObstacle getBottomWallInstance(Vector3f pos) {
        return new WallObstacle(pos, new Quat4f(0, -0.7071f, 0, 0.7071f));
    }

    private static WallObstacle getLeftWallInstance(Vector3f pos) {
        return new WallObstacle(pos, new Quat4f(0, 0, 0, 1));
    }

    private static MovingWallObstacle getMovingWallObstacle(Vector3f pos) {
        return new MovingWallObstacle(pos, new Quat4f(0,0,0,1));
    }

    public static WallObstacle getRandomWallInstance(Vector3f pos) {
        final Random rand = new Random();

        /*int randomInt = (int) (rand.nextFloat() * 5);


        if (randomInt == 0) {
            return getTopWallInstance(pos);
        } else if (randomInt == 1) {
            return getRightWallInstance(pos);
        } else if (randomInt == 2) {
            return getBottomWallInstance(pos);
        } else if(randomInt == 3) {
            return getLeftWallInstance(pos);
        }else{
            return crazyWall(pos);
            */
        return getMovingWallObstacle(pos);

    }
}

