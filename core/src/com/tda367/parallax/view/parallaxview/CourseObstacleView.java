package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.core.world.courseobstacles.CourseObstacleBase;
import com.tda367.parallax.model.core.world.courseobstacles.ObstacleType;
import com.tda367.parallax.view.rendering.Renderer3D;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.utilities.ResourceLoader;

/**
 * View class for {@link CourseObstacleBase}.
 */
public class CourseObstacleView implements View{
    private final CourseObstacleBase obstacle;
    private Renderable3dObject renderable3dObject;

    public CourseObstacleView(CourseObstacleBase obstacle){
        this.obstacle = obstacle;
        renderable3dObject = new Renderable3dObject(
                obstacle.getPos(),
                obstacle.getRot(),
                ResourceLoader.getInstance().getModel(get3dModelPath(obstacle.getObstacleType())),
                1f,
                true
        );
    }


    @Override
    public void render() {
        if(!obstacle.isDestroyed()) {
            renderable3dObject.setPos(obstacle.getPos());
            renderable3dObject.setRot(obstacle.getRot());
            Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
        }
    }
    @Override
    public boolean isObsolete() {
        return obstacle.isDestroyed();
    }

    private String get3dModelPath(ObstacleType type){
        if (type == ObstacleType.BOX) {
            return "3dModels/box/box.g3db";
        } else if (type == ObstacleType.WALL){
            return "3dModels/wall/wall.g3db";
        } else {
            return "3dModels/box/box.g3db";
        }

    }
}
