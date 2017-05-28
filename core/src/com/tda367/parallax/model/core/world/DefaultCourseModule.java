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

    private static final float DEFAULT_LENGTH = 64;
    private static final int DEFAULT_OBSTACLE_AMOUNT = 4;
    private static final int DEFAULT_POWER_UP_AMOUNT = 1;

    private Vector3f pos;
    private Quat4f rot;
    private float length;

    private List<CourseObstacleBase> couseObstacles;
    private List<IPowerUp> powerUps;
    private boolean active;
    private List<Container> containers;
    private final Random rand;

    DefaultCourseModule(Vector3f pos, int obstacleAmmount, int powerupsToSpawn) {
        this.pos = pos;
        this.pos.setY(pos.getY());
        this.rot = new Quat4f();
        this.active = true;
        this.powerUps = new ArrayList<IPowerUp>();
        this.containers = new ArrayList<Container>();
        this.length = DEFAULT_LENGTH;
        this.couseObstacles = new ArrayList<CourseObstacleBase>();
        this.rand = new Random();

        addObstacles(obstacleAmmount);

        for (int i = 0; i < powerupsToSpawn; i++) {
            spawnPowerUp();
        }
    }

    public DefaultCourseModule(Vector3f pos) {
        this(pos, DEFAULT_OBSTACLE_AMOUNT, DEFAULT_POWER_UP_AMOUNT);
    }


    private void addObstacles(int amount) {
        this.couseObstacles.addAll(createWalls(amount));
        this.couseObstacles.addAll(createBoxes(amount));
    }

    private List<CourseObstacleBase> createBoxes(int amount) {

        final List<CourseObstacleBase> boxes = new ArrayList<CourseObstacleBase>();
        for (int x = 0; x < amount; x++) {
            final Vector3f obstaclePos = new Vector3f(
                    0,
                    this.pos.getY() - this.length / 2 + this.length * this.rand.nextFloat(),
                    0
            );
            final CourseObstacleBase obstacle = ObstacleFactory.getMovingBoxInstance(obstaclePos, true);

            obstacle.getPos().set(obstaclePos);
            boxes.add(obstacle);
        }
        return boxes;
    }
    private List<CourseObstacleBase> createWalls(int amount) {
        final float distanceBetween = this.length / (float) amount;

        final List<CourseObstacleBase> walls = new ArrayList<CourseObstacleBase>();
        for (int x = 0; x < amount; x++) {
            final Vector3f obstaclePos = new Vector3f(
                    0,
                    this.pos.getY() - this.length / 2 + x * distanceBetween,
                    0
            );
            final CourseObstacleBase obstacle = ObstacleFactory.getRandomWallInstance(obstaclePos);

            obstacle.getPos().set(obstaclePos);
            walls.add(obstacle);
        }
        return walls;
    }


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
        final IPowerUp powerUp = PowerUpFactory.createRandomPowerUp();
        this.powerUps.add(powerUp);
        final Container container = new Container(powerUp);
        final Random rand = new Random();
        container.setPos(new Vector3f(0, getPos().getY() + rand.nextFloat() * this.length, 0));
        container.addToCollisionManager();
        this.containers.add(container);
    }

    @Override
    public Vector3f getPos() {
        return this.pos;
    }

    @Override
    public Quat4f getRot() {
        return this.rot;
    }
}
