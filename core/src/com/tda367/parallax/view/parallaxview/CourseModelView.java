package com.tda367.parallax.view.parallaxview;

import com.tda367.parallax.model.core.powerups.container.Container;
import com.tda367.parallax.model.core.world.ICourseModule;
import com.tda367.parallax.model.core.world.courseobstacles.CourseObstacleBase;
import com.tda367.parallax.utilities.ResourceLoader;
import com.tda367.parallax.view.rendering.Renderable3dObject;
import com.tda367.parallax.view.rendering.Renderer3D;

import java.util.ArrayList;
import java.util.List;

/**
 * View class for {@link ICourseModule}.
 */
public class CourseModelView implements View {

    private static final String MODEL_3D_INTERNAL_PATH = "3dModels/defaultCourse/course.g3db";
    private final ICourseModule courseModule;
    private List<CourseObstacleView> obstacleViews;
    private List<ContainerView> containerViews;
    private Renderable3dObject courseModule3dObject;

    /**
     * Creates a CourseModelView from an {@link ICourseModule}.
     *
     * @param courseModule to be used to create the CourseModelView.
     */
    CourseModelView(ICourseModule courseModule) {
        this.courseModule = courseModule;
        this.obstacleViews = new ArrayList<CourseObstacleView>();
        this.containerViews = new ArrayList<ContainerView>();
        this.courseModule3dObject = new Renderable3dObject(
                courseModule.getPos(),
                courseModule.getRot(),
                ResourceLoader.getInstance().getModel(MODEL_3D_INTERNAL_PATH),
                1f,
                true
        );

        for (final CourseObstacleBase obstacle : courseModule.getCouseObstacles()) {
            this.obstacleViews.add(new CourseObstacleView(obstacle));
        }

        for (final Container container : courseModule.getContainers()) {
            this.containerViews.add(new ContainerView(container));
        }
    }

    @Override
    public void render() {
        //Render course module.
        Renderer3D.getInstance().addObjectToFrame(this.courseModule3dObject);

        //Render obstacles
        for (final CourseObstacleView obstacle : this.obstacleViews) {
            obstacle.render();
        }

        //Render containers
        for (final ContainerView container : this.containerViews) {
            container.render();
        }

    }

    @Override
    public boolean isObsolete() {
        return !this.courseModule.isActive();
    }
}
