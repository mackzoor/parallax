package com.tda367.parallax.model.parallaxCore.course;

import com.tda367.parallax.model.CoreAbstraction.Collidable;
import com.tda367.parallax.model.parallaxCore.spaceCraft.ISpaceCraft;
import com.tda367.parallax.model.parallaxCore.Collision.CollisionPair;
import com.tda367.parallax.model.parallaxCore.Collision.ICollisionCalculator;
import com.tda367.parallax.model.CoreAbstraction.RenderManager;
import com.tda367.parallax.model.CoreAbstraction.SoundManager;
import com.tda367.parallax.model.CoreAbstraction.Updatable;
import com.tda367.parallax.model.parallaxCore.powerUps.IPowerUp;
import com.tda367.parallax.model.parallaxCore.spaceCraft.SpaceCraftListener;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * A course that handles the visual representation and updating of {@link ISpaceCraft} and {@link Collidable}.
 */
public class Course implements Updatable, SpaceCraftListener {
    private List<ICourseModule> modules;
    private List<ISpaceCraft> spaceCrafts;
    private ICollisionCalculator collisionCalculator;
    //TODO, remove the power-up after used
    private List<IPowerUp> activePowerups;


    public Course(){
        modules = new ArrayList<ICourseModule>();
        spaceCrafts = new ArrayList<ISpaceCraft>();
        collisionCalculator = null;

        updateModuleRange();
        activePowerups = new ArrayList<IPowerUp>();
    }

    public List<ISpaceCraft> getSpaceCrafts() {
        return spaceCrafts;
    }
    public void addSpaceCraft(ISpaceCraft spaceCraft) {
        spaceCrafts.add(spaceCraft);
        spaceCraft.addSpaceCraftListener(this);
        RenderManager.getInstance().addRenderTask(spaceCraft);
    }
    public void removeSpaceCraft(ISpaceCraft spaceCraft) {
        spaceCrafts.remove(spaceCraft);
        spaceCraft.removeSpaceCraftListener(this);
        RenderManager.getInstance().removeRenderTask(spaceCraft);
    }
    private float getFirstSpaceCraftYPosition() {
        if (spaceCrafts.size() > 0) {
            float yPosition = spaceCrafts.get(0).getPos().getY();
            for (int i = 1; i < spaceCrafts.size(); i++) {
                float tempYPosition = spaceCrafts.get(i).getPos().getY();
                if (tempYPosition > yPosition) {
                    yPosition = tempYPosition;
                }
            }
            return yPosition;
        } else {
            return 0;
        }
    }
    private float getLastSpaceCraftYPosition() {
        if (spaceCrafts.size() > 0) {
            float yPosition = spaceCrafts.get(0).getPos().getY();
            for (int i = 1; i < spaceCrafts.size(); i++) {
                float tempYPosition = spaceCrafts.get(i).getPos().getY();
                if (tempYPosition < yPosition) {
                    yPosition = tempYPosition;
                }
            }
            return yPosition;
        } else {
            return 0;
        }
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        for (ISpaceCraft spaceCraft : spaceCrafts) {
            spaceCraft.update(milliSinceLastUpdate);
        }

        for (IPowerUp pu : activePowerups) {
            pu.update(milliSinceLastUpdate);
        }


        if (collisionCalculator != null){
            List<Collidable> obstacleList = new ArrayList<Collidable>();

            for (ICourseModule module : modules){
                obstacleList.addAll((module.getBoxObstacles()));
                obstacleList.addAll(module.getUsables());
            }

            List<CollisionPair> collisionList = collisionCalculator.getCollisions(obstacleList, spaceCrafts);

            if (collisionList.size() > 0){
                SoundManager.getInstance().playSound("flashBang.mp3","sounds/effects", 0.2f);
            }

            for (CollisionPair pair : collisionList){
                pair.getFirstCollidable().disableCollision();
            }


        }

        updateModuleRange();
    }
    private void updateModuleRange() {

        if (modules.size() > 0) {
            float firstCraft = getFirstSpaceCraftYPosition();
            float lastCraft = getLastSpaceCraftYPosition();

            float firstModule = modules.get(modules.size() - 1).getPos().getY() + modules.get(modules.size() - 1).getLength();
            float lastModule = modules.get(0).getPos().getY();

            int modulesToAdd = (int) ((firstCraft + 256 - firstModule) / 64);
            int modulesToRemove = (int) ((lastCraft - lastModule) / 128);


            addModules(modulesToAdd);
            removeModules(modulesToRemove);
        } else {
            ICourseModule defModule = new DefaultCourseModule(new Vector3f(0,-32,0));
            modules.add(defModule);
            updateModuleRange();
        }

    }
    private void addModules(int i) {
        for (int x = 0; x < i; x++) {
            float endOfLastModulePos = modules.get(modules.size() - 1).getPos().getY();
            ICourseModule tempModule = new DefaultCourseModule(new Vector3f(
                    0,
                    endOfLastModulePos+modules.get(modules.size()-1).getLength(),
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

    public void setCollisionCalculator(ICollisionCalculator collisionCalculator) {
        this.collisionCalculator = collisionCalculator;
    }


    public void powerUPUsed(IPowerUp pu) {
        if (activePowerups.indexOf(pu) == -1) {
            activePowerups.add(pu);
        }
    }

}
