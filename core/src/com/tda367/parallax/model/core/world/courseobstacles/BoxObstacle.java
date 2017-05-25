package com.tda367.parallax.model.core.world.courseobstacles;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * A cube that is renderable and collidable.
 */
class BoxObstacle extends CourseObstacleBase {

    private final static String COLLISION_MODEL_PATH = "3dModels/box/hitbox.obj";

    BoxObstacle(Vector3f pos, Quat4f rot) {
        super(pos, rot);
    }

    @Override
    public String getCollisionModelPath() {
        return COLLISION_MODEL_PATH;
    }

    @Override
    public ObstacleType getObstacleType() {
        return ObstacleType.BOX;
    }
}
