package com.tda367.parallax.model.core.world.courseobstacles;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * A wall that is renderable and collidable.
 */
public class WallObstacle extends CourseObstacleBase {

    private final static String COLLISION_MODEL_PATH = "3dModels/wall/hitbox.obj";

    WallObstacle(Vector3f pos, Quat4f rot) {
        super(pos, rot);
    }

    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL_PATH;
    }

    @Override
    public ObstacleType getObstacleType() {
        return ObstacleType.WALL;
    }
}
