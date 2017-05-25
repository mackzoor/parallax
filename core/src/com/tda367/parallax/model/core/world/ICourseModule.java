package com.tda367.parallax.model.core.world;

import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.powerups.container.Container;
import com.tda367.parallax.model.core.util.Transformable;
import com.tda367.parallax.model.core.world.courseobstacles.CourseObstacleBase;

import java.util.List;

/**
 * A single module of a course in the game. Will automatically create usables and obstacles.
 */

public interface ICourseModule extends Transformable {
    List<? extends CourseObstacleBase> getCouseObstacles();

    float getLength();

    void add3dObjectsToCollisionManager();

    void remove3dObjectsFromCollisionManager();

    List<IPowerUp> getPowerups();

    List<Container> getContainers();

    boolean isActive();

    void setActiveState(boolean state);
}
