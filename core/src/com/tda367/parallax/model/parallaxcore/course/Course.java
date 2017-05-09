package com.tda367.parallax.model.parallaxcore.course;

import com.tda367.parallax.model.parallaxcore.collision.*;
import com.tda367.parallax.model.parallaxcore.spacecraft.ISpaceCraft;
import com.tda367.parallax.model.coreabstraction.Updatable;
import com.tda367.parallax.model.parallaxcore.powerups.IPowerUp;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * A course that handles the visual representation and updating of {@link ISpaceCraft} and {@link Collidable}.
 */

public class Course implements Updatable, CollisionObserver {
    private List<ICourseModule> modules;
    private List<ISpaceCraft> spaceCrafts;
    //TODO, remove the power-up after used
    private List<IPowerUp> activePowerups;

    public Course(){
        CollisionManager.getInstance().subscribeToCollisions(this);
        modules = new ArrayList<ICourseModule>();
        spaceCrafts = new ArrayList<ISpaceCraft>();

        updateModuleRange();
        activePowerups = new ArrayList<IPowerUp>();
    }

    public List<ISpaceCraft> getSpaceCrafts() {
        return spaceCrafts;
    }
    public void addSpaceCraft(ISpaceCraft spaceCraft) {
        spaceCrafts.add(spaceCraft);
        spaceCraft.addToCollisionManager();
        spaceCraft.addToRenderManager();
    }
    public void removeSpaceCraft(ISpaceCraft spaceCraft) {
        spaceCrafts.remove(spaceCraft);
        spaceCraft.addToRenderManager();
        spaceCraft.addToCollisionManager();
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
            if (pu.isActive()){
                pu.update(milliSinceLastUpdate);
            }
            if (pu.isDead()){
                activePowerups.remove(pu);
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
            tempModule.add3dObjectsToCollisionManager();
        }
    }
    private void removeModules(int i) {
        for (int x = 0; x < i; x++) {
            ICourseModule module = modules.get(0);
            module.removeFromRenderManager();
            modules.remove(module);
            module.remove3dObjectsFromCollisionManager();
        }
    }

    @Override
    public void respondToCollision(CollisionPair collisionPair) {
        Collidable first = collisionPair.getFirstCollidable();
        Collidable second = collisionPair.getSecondCollidable();

        first.handleCollision(second.getCollidableType());
        second.handleCollision(first.getCollidableType());
    }
}
