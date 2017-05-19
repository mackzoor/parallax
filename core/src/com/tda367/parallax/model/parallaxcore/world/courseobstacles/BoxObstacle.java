package com.tda367.parallax.model.parallaxcore.world.courseobstacles;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * A cube that is renderable and collidable.
 */
class BoxObstacle extends CourseObstacleBase {

    private final String collisionModelPath = "3dModels/boxObstacle/boxObstacle.g3db";

    BoxObstacle(Vector3f pos, Quat4f rot){
        super(pos, rot);
    }

    @Override
    public String getCollisionModelPath() {
        return collisionModelPath;
    }
    @Override
    public ObstacleType getObstacleType() {
        return ObstacleType.BOX;
    }
}
