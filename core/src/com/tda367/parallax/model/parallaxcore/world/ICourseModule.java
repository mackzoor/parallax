package com.tda367.parallax.model.parallaxcore.world;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.powerups.Container;
import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;
import com.tda367.parallax.model.parallaxcore.util.Transformable;
import com.tda367.parallax.view.Renderable;

import java.util.List;

/**
 * A single module of a course in the game. Will automatically create usables and obstacles.
 */

public interface ICourseModule extends Transformable{
    List<? extends Collidable> getBoxObstacles();
    float getLength();
    void add3dObjectsToCollisionManager();
    void remove3dObjectsFromCollisionManager();
    List<IPowerUp> getPowerups();
    List<Container> getContainers();
    boolean isActive();
    void setActiveState(boolean state);
}
