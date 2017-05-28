package com.tda367.parallax.view.parallaxview.parallaxviewcomponents.worldview.courseviews.obstacleview;

import com.tda367.parallax.model.core.world.courseobstacles.CourseObstacleBase;
import com.tda367.parallax.model.core.world.courseobstacles.ObstacleType;
import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

/**
 * View class for {@link CourseObstacleBase}.
 */
public class CourseObstacleView {
    private final CourseObstacleBase obstacle;
    private final Renderable3dObject renderable3dObject;

    public CourseObstacleView(CourseObstacleBase obstacle) {
        this.obstacle = obstacle;
        this.renderable3dObject = new Renderable3dObject(
                obstacle.getPos(),
                obstacle.getRot(),
                ResourceLoader.getInstance().getModel(get3dModelPath(obstacle.getObstacleType())),
                0.75f,
                true
        );
    }

    public void render() {
        if (!this.obstacle.isDestroyed()) {
            this.renderable3dObject.setPos(this.obstacle.getPos());
            this.renderable3dObject.setRot(this.obstacle.getRot());
            Renderer3D.getInstance().addObjectToFrame(this.renderable3dObject);
        }
    }

    public boolean isDestroyed() {
        return this.obstacle.isDestroyed();
    }

    private String get3dModelPath(ObstacleType type) {
        if (type == ObstacleType.BOX) {
            return "3dModels/box/box.g3db";
        } else if (type == ObstacleType.WALL) {
            return "3dModels/wall/wall.g3db";
        } else {
            return "3dModels/box/box.g3db";
        }

    }
}
