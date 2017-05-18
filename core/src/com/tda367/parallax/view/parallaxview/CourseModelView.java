package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.powerups.Container;
import com.tda367.parallax.model.parallaxcore.world.ICourseModule;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.util.Renderable3dObject;
import com.tda367.parallax.view.util.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * View class for {@link ICourseModule}.
 */
public class CourseModelView implements View{
    private final String model3dInternalPath = "3dModels/defaultCourse/course.g3db";
    private final ICourseModule courseModule;
    private List<BoxObstacleView> obstacleViews;
    private List<ContainerView> containerViews;
    private Renderable3dObject courseModule3dObject;

    CourseModelView(ICourseModule courseModule) {
        this.courseModule = courseModule;
        this.obstacleViews = new ArrayList<BoxObstacleView>();
        this.containerViews = new ArrayList<ContainerView>();
        courseModule3dObject = new Renderable3dObject(
                courseModule.getPos(),
                courseModule.getRot(),
                ResourceLoader.getInstance().getModel(model3dInternalPath),
                1f,
                true
        );

        for (Collidable collidable : courseModule.getBoxObstacles()) {
            obstacleViews.add(new BoxObstacleView(collidable));
        }

        for (Container container : courseModule.getContainers()) {
            containerViews.add(new ContainerView(container));
        }
    }

    @Override
    public void render() {
        //Render course module.
        Renderer3D.getInstance().addObjectToFrame(courseModule3dObject);

        //Render obstacles
        for (BoxObstacleView obstacle : obstacleViews) {
            obstacle.render();
        }

        //Render containers
        for (ContainerView container : containerViews) {
            container.render();
        }

    }
    @Override
    public boolean isObsolete() {
        return !courseModule.isActive();
    }
}
