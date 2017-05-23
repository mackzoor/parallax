package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.core.powerups.Container;
import com.tda367.parallax.model.core.world.ICourseModule;
import com.tda367.parallax.model.core.world.courseobstacles.CourseObstacleBase;
import com.tda367.parallax.view.rendering.Renderer3D;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.utilities.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * View class for {@link ICourseModule}.
 */
public class CourseModelView implements View{

    private final static String MODEL_3D_INTERNAL_PATH = "3dModels/defaultCourse/course.g3db";
    private final ICourseModule courseModule;
    private List<CourseObstacleView> obstacleViews;
    private List<ContainerView> containerViews;
    private Renderable3dObject courseModule3dObject;

    /**
     * Creates a CourseModelView from an {@link ICourseModule}.
     * @param courseModule to be used to create the CourseModelView.
     */
    CourseModelView(ICourseModule courseModule) {
        this.courseModule = courseModule;
        this.obstacleViews = new ArrayList<CourseObstacleView>();
        this.containerViews = new ArrayList<ContainerView>();
        courseModule3dObject = new Renderable3dObject(
                courseModule.getPos(),
                courseModule.getRot(),
                ResourceLoader.getInstance().getModel(MODEL_3D_INTERNAL_PATH),
                1f,
                true
        );

        for (CourseObstacleBase obstacle : courseModule.getCouseObstacles()) {
            obstacleViews.add(new CourseObstacleView(obstacle));
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
        for (CourseObstacleView obstacle : obstacleViews) {
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
