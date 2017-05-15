package com.tda367.parallax.model.parallaxcore.world;

import com.tda367.parallax.model.parallaxcore.collision.Collidable;
import com.tda367.parallax.model.parallaxcore.powerups.Container;
import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;
import com.tda367.parallax.model.parallaxcore.powerups.Missile;


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

    private List<BoxObstacle> boxObstacles;
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
        this.boxObstacles = new ArrayList<BoxObstacle>();
        usables = new ArrayList<Collidable>();

        addObstacles(obstacleAmmount);

        for (int i = 0; i < powerupsToSpawn; i++) {
            spawnPowerUp();
        }
    }
    public DefaultCourseModule(Vector3f pos){
        this(pos,4, 1);
    }

    private void addObstacles(int i){
        for (int x = 0; x < i; x++){
            BoxObstacle boxObstacleNew = new BoxObstacle();

            Random rand = new Random();
            Vector3f obstaclePos = new Vector3f(
                    rand.nextFloat()*12-6,
                    rand.nextFloat()*length-length/2+(this.pos.getY()),
                    rand.nextFloat()*12-6
            );

            boxObstacleNew.getPos().set(obstaclePos);

            this.boxObstacles.add(boxObstacleNew);
        }
    }

    //ICourseModule
    @Override
    public float getLength(){
        return length;
    }


    @Override
    public void add3dObjectsToCollisionManager() {
        for (BoxObstacle boxObstacle : boxObstacles) {
            boxObstacle.addToCollisionManager();
        }
    }
    @Override
    public void remove3dObjectsFromCollisionManager() {
        for (BoxObstacle boxObstacle : boxObstacles) {
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
    }
    @Override
    public List<? extends Collidable> getBoxObstacles() {
        return boxObstacles;
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
