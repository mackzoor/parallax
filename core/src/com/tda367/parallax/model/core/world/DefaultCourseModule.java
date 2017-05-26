package com.tda367.parallax.model.core.world;

import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.powerups.arsenal.PowerUpFactory;
import com.tda367.parallax.model.core.powerups.container.Container;
import com.tda367.parallax.model.core.world.courseobstacles.CourseObstacleBase;
import com.tda367.parallax.model.core.world.courseobstacles.ObstacleFactory;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A course module that will represent the visual part of a course.
 */

public class DefaultCourseModule implements ICourseModule {
    private Vector3f pos;
    private Quat4f rot;
    private float length;

    private List<CourseObstacleBase> couseObstacles;
    private List<IPowerUp> powerUps;
    private boolean active;
    private List<Container> containers;


    DefaultCourseModule(Vector3f pos, int obstacleAmmount, int powerupsToSpawn) {
        this.pos = pos;
        this.pos.setY(pos.getY());
        this.rot = new Quat4f();
        this.active = true;
        this.powerUps = new ArrayList<IPowerUp>();
        this.containers = new ArrayList<Container>();
        this.length = 64;
        this.couseObstacles = new ArrayList<CourseObstacleBase>();

        addObstacles(obstacleAmmount);

        for (int i = 0; i < powerupsToSpawn; i++) {
            spawnPowerUp();
        }
    }

    public DefaultCourseModule(Vector3f pos) {
        this(pos, 4, 1);
    }


    private void addObstacles(int amount) {

        final float distanceBetween = this.length / (float) amount;
        for (int x = 0; x < amount; x++) {
            final Vector3f obstaclePos = new Vector3f(
                    0,
                    this.pos.getY() - this.length / 2 + x * distanceBetween,
                    0
            );
            final CourseObstacleBase obstacle = ObstacleFactory.getRandomWallInstance(obstaclePos);

            obstacle.getPos().set(obstaclePos);
            this.couseObstacles.add(obstacle);
        }
    }


    //ICourseModule
    @Override
    public float getLength() {
        return this.length;
    }


    @Override
    public void add3dObjectsToCollisionManager() {
        for (final CourseObstacleBase boxObstacle : this.couseObstacles) {
            boxObstacle.addToCollisionManager();
        }
    }

    @Override
    public void remove3dObjectsFromCollisionManager() {
        for (final CourseObstacleBase boxObstacle : this.couseObstacles) {
            boxObstacle.removeFromCollisionManager();
        }

    }


    @Override
    public List<IPowerUp> getPowerups() {
        return this.powerUps;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActiveState(boolean state) {
        this.active = state;
        if (!state) {
            for (final Container container : this.containers) {
                container.removeFromCollisionManager();
            }
        }
    }

    @Override
    public List<? extends CourseObstacleBase> getCouseObstacles() {
        return this.couseObstacles;
    }

    @Override
    public List<Container> getContainers() {
        return this.containers;
    }

    private void spawnPowerUp() {
        final IPowerUp pu = PowerUpFactory.createRandom();
        this.powerUps.add(pu);
        final Container container = new Container(pu);
        final Random rand = new Random();
        container.setPos(new Vector3f(0, getPos().getY() + rand.nextFloat() * length, 0));
        container.addToCollisionManager();
        this.containers.add(container);
    }

    //Transformable
    @Override
    public Vector3f getPos() {
        return this.pos;
    }

    @Override
    public Quat4f getRot() {
        return this.rot;
    }
}
