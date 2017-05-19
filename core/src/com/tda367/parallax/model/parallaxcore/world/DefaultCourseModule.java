package com.tda367.parallax.model.parallaxcore.world;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.powerups.Container;
import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;
import com.tda367.parallax.model.parallaxcore.powerups.Missile;
import com.tda367.parallax.model.parallaxcore.world.courseobstacles.CourseObstacleBase;
import com.tda367.parallax.model.parallaxcore.world.courseobstacles.ObstacleFactory;


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
    private List<Collidable> usables;
    private List<IPowerUp> powerUps;
    private boolean active;
    private List<Container> containers;


    DefaultCourseModule(Vector3f pos,int obstacleAmmount, int powerupsToSpawn){
        this.pos = pos;
        this.pos.setY(pos.getY());
        this.rot = new Quat4f();
        active = true;
        powerUps = new ArrayList<IPowerUp>();
        containers = new ArrayList<Container>();
        length = 64;
        this.couseObstacles = new ArrayList<CourseObstacleBase>();
        usables = new ArrayList<Collidable>();

        addObstacles(obstacleAmmount);

        for (int i = 0; i < powerupsToSpawn; i++) {
            spawnPowerUp();
        }
    }
    public DefaultCourseModule(Vector3f pos){
        this(pos,4, 1);
    }


    public void addObstacles(int i) {
        for (int x = 0; x < i; x++) {
            Random rand = new Random();
            Vector3f obstaclePos = new Vector3f(
                    0,
                    rand.nextFloat() * length - length / 2 + (this.pos.getY()),
                    0
            );
            CourseObstacleBase obstacle = ObstacleFactory.getRandomWallInstance(obstaclePos);

            obstacle.getPos().set(obstaclePos);
            this.couseObstacles.add(obstacle);
        }
    }


    //ICourseModule
    @Override
    public float getLength(){
        return length;
    }


    @Override
    public void add3dObjectsToCollisionManager() {
        for (CourseObstacleBase boxObstacle : couseObstacles) {
            boxObstacle.addToCollisionManager();
        }
    }
    @Override
    public void remove3dObjectsFromCollisionManager() {
        for (CourseObstacleBase boxObstacle : couseObstacles) {
            boxObstacle.removeFromCollisionManager();
        }

    }


    @Override
    public List<IPowerUp> getPowerups() {
        return powerUps;
    }
    @Override
    public boolean isActive() {
        return active;
    }
    @Override
    public void setActiveState(boolean state) {
        active = state;
        if (!state){
            for (Container container : containers) {
                container.removeFromCollisionManager();
            }
        }
    }
    @Override
    public List<? extends CourseObstacleBase> getCouseObstacles() {
        return couseObstacles;
    }
    @Override
    public List<Container> getContainers() {
        return containers;
    }

    private void spawnPowerUp() {
        IPowerUp pu = new Missile();
        powerUps.add(pu);
        Container container = new Container(pu);
        Random rand = new Random();
        container.setPos(new Vector3f(0,getPos().getY()+rand.nextFloat()*getLength(),0));
        container.addToCollisionManager();
        containers.add(container);
    }

    //Transformable
    @Override
    public Vector3f getPos() {
        return pos;
    }
    @Override
    public Quat4f getRot() {
        return rot;
    }
}
