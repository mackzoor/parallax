package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.parallaxCore.Collidable;
import com.tda367.parallax.parallaxCore.Collision.CollisionPair;
import com.tda367.parallax.parallaxCore.Collision.ICollisionCalculator;
import com.tda367.parallax.parallaxCore.RenderManager;
import com.tda367.parallax.parallaxCore.SoundManager;
import com.tda367.parallax.parallaxCore.enemies.MinionEnemy;
import com.tda367.parallax.parallaxCore.Updatable;
import com.tda367.parallax.parallaxCore.spaceCraft.Agelion;
import com.tda367.parallax.parallaxCore.spaceCraft.ISpaceCraft;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that binds together different course modules and creates several enemy ai's..
 */
public class Course implements Updatable {

    private List<ICourseModule> modules;
    private List<ISpaceCraft> spaceCrafts;
    private ICollisionCalculator collisionCalculator;


    public Course() {
        modules = new ArrayList<ICourseModule>();
        spaceCrafts = new ArrayList<ISpaceCraft>();
        collisionCalculator = null;

        updateModuleRange();

        //Debug purpose only
//        createTestEnemy();
    }

    public void addSpaceCraft(ISpaceCraft spaceCraft) {
        spaceCrafts.add(spaceCraft);
        RenderManager.getInstance().addRenderTask(spaceCraft);
    }

    public void removeSpaceCraft(ISpaceCraft spaceCraft) {
        spaceCrafts.remove(spaceCraft);
        RenderManager.getInstance().removeRenderTask(spaceCraft);
    }

    @Override
    public void update(int milliSinceLastUpdate) {

        for (ISpaceCraft spaceCraft : spaceCrafts) {
            spaceCraft.update(milliSinceLastUpdate);
        }


        if (collisionCalculator != null){
            List<Collidable> obstacleList = new ArrayList<Collidable>();

            for (ICourseModule module : modules){
                obstacleList.addAll((module.getObstacles()));
                obstacleList.addAll(module.getUsables());
            }

            List<CollisionPair> collisionList = collisionCalculator.getCollisions(obstacleList, spaceCrafts);

            if (collisionList.size() > 0){
                SoundManager.getInstance().playSound("flashBang.mp3","sounds/effects");
            }

            for (CollisionPair pair : collisionList){
                pair.getColl1().disableCollision();
            }


        }

        updateModuleRange();
    }

    private void updateModuleRange() {

        if (modules.size() > 0) {
            float firstCraft = getFirstSpaceCraftDistance();
            float lastCraft = getLastSpaceCraftDistance();

            float firstModule = modules.get(modules.size() - 1).getPos().getY() + modules.get(modules.size() - 1).getLength();
            float lastModule = modules.get(0).getPos().getY();

            int modulesToAdd = (int) ((firstCraft - firstModule + 256) / 64);
            int modulesToRemove = (int) ((lastCraft - lastModule) / 16);


            addModules(modulesToAdd);
            removeModules(modulesToRemove);
        } else {
            ICourseModule defModule = new DefaultCourseModule(new Vector3f());
            modules.add(defModule);
            updateModuleRange();
        }

    }

    private void addModules(int i) {
        for (int x = 0; x < i; x++) {
            float endOfLastModulePos = modules.get(modules.size() - 1).getPos().getY() + modules.get(modules.size() - 1).getLength() / 2;
            ICourseModule tempModule = new DefaultCourseModule(new Vector3f(
                    0,
                    endOfLastModulePos,
                    0
            ));
            modules.add(tempModule);
            tempModule.addToRenderManager();
        }
    }

    private void removeModules(int i) {
        for (int x = 0; x < i; x++) {
            ICourseModule module = modules.get(0);
            module.removeFromRenderManager();
            modules.remove(module);
        }
    }

    public List<ISpaceCraft> getSpaceCrafts() {
        return spaceCrafts;
    }

    private void createTestEnemy() {
        Random rand = new Random();

        MinionEnemy minionEnemy = new MinionEnemy(new Agelion(
                new Vector3f(1.5f, -2, 1),
                new Quat4f(),
                3
        ));
        minionEnemy.getSpaceCraft().setAcceleration(-0.5f);
//        minionEnemy.setTarget(spaceCrafts.get(0));
        spaceCrafts.add(minionEnemy.getSpaceCraft());
        RenderManager.getInstance().addRenderTask(minionEnemy.getSpaceCraft());
    }

    private float getFirstSpaceCraftDistance() {
        if (spaceCrafts.size() > 0) {
            float dist = spaceCrafts.get(0).getPos().getY();
            for (int i = 1; i < spaceCrafts.size(); i++) {
                float tempDist = spaceCrafts.get(i).getPos().getY();
                if (tempDist > dist) {
                    dist = tempDist;
                }
            }
            return dist;
        } else {
            return 0;
        }
    }

    private float getLastSpaceCraftDistance() {
        if (spaceCrafts.size() > 0) {
            float dist = spaceCrafts.get(0).getPos().getY();
            for (int i = 1; i < spaceCrafts.size(); i++) {
                float tempDist = spaceCrafts.get(i).getPos().getY();
                if (tempDist < dist) {
                    dist = tempDist;
                }
            }
            return dist;
        } else {
            return 0;
        }
    }

    public void setCollisionCalculator(ICollisionCalculator collisionCalculator) {
        this.collisionCalculator = collisionCalculator;
    }

    //TODO Check collisions between spacecraft and obstacles

}
