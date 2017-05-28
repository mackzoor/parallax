package com.tda367.parallax.model.core.world.courseobstacles;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.Random;

/**
 * Factory for creating obstacles.
 */
public final class ObstacleFactory {

    private static final Quat4f TOP_WALLROTATION = new Quat4f(0, 0.7071f, 0, 0.7071f);
    private static final Quat4f RIGHT_WALLROTATION = new Quat4f(0, 1, 0, 0);
    private static final Quat4f BOTTOM_WALLROTATION = new Quat4f(0, -0.7071f, 0, 0.7071f);
    private static final Quat4f LEFT_WALLROTATION = new Quat4f(0, 0, 0, 1);
    private static Random rand = new Random();

    private ObstacleFactory() {
    }

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


    private static WallObstacle getTopWallInstance(Vector3f pos) {
        return new WallObstacle(pos, new Quat4f(TOP_WALLROTATION));
    }

    private static WallObstacle getRightWallInstance(Vector3f pos) {
        return new WallObstacle(pos, new Quat4f(RIGHT_WALLROTATION));
    }

    private static WallObstacle getBottomWallInstance(Vector3f pos) {
        return new WallObstacle(pos, new Quat4f(BOTTOM_WALLROTATION));
    }

    private static WallObstacle getLeftWallInstance(Vector3f pos) {
        return new WallObstacle(pos, new Quat4f(LEFT_WALLROTATION));
    }

    private static MovingWallObstacle getMovingWallInstance(Vector3f pos) {
        return new MovingWallObstacle(pos, new Quat4f(LEFT_WALLROTATION));
    }

    public static WallObstacle getRandomWallInstance(Vector3f pos) {
        final int randomInt = rand.nextInt(5);
        WallObstacle returnWall;
        switch (randomInt) {
            case 0:
                returnWall = getBottomWallInstance(pos);
                break;
            case 1:
                returnWall = getLeftWallInstance(pos);
                break;
            case 2:
                returnWall = getRightWallInstance(pos);
                break;
            case 3:
                returnWall = getMovingWallInstance(pos);
                break;
            default:
                returnWall = getTopWallInstance(pos);
                break;
        }
        return returnWall;
    }
}

