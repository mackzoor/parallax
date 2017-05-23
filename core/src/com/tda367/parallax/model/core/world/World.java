package com.tda367.parallax.model.core.world;

import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import lombok.Getter;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * A course that handles the visual representation and updating of {@link com.tda367.parallax.model.core.spacecraft.ISpaceCraft} and {@link com.tda367.parallax.model.core.collision.Collidable}.
 */

public class World implements com.tda367.parallax.model.core.util.Updatable, com.tda367.parallax.model.core.collision.CollisionObserver {
    @Getter private List<ICourseModule> modules;
    @Getter private List<com.tda367.parallax.model.core.spacecraft.ISpaceCraft> spaceCrafts;
    //TODO, remove the power-up after used
    @Getter private List<IPowerUp> powerUps;

    public World(){
        com.tda367.parallax.model.core.collision.CollisionManager.getInstance().subscribeToCollisions(this);
        modules = new ArrayList<ICourseModule>();
        spaceCrafts = new ArrayList<com.tda367.parallax.model.core.spacecraft.ISpaceCraft>();
        powerUps = new ArrayList<IPowerUp>();

        updateModuleRange();
    }

    public void addSpaceCraft(com.tda367.parallax.model.core.spacecraft.ISpaceCraft spaceCraft) {
        spaceCrafts.add(spaceCraft);
        spaceCraft.addToCollisionManager();
    }
    public void removeSpaceCraft(com.tda367.parallax.model.core.spacecraft.ISpaceCraft spaceCraft) {
        spaceCrafts.remove(spaceCraft);
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
            ICourseModule defModule = new DefaultCourseModule(new Vector3f(0,32,0),0,0);
            modules.add(defModule);
            for (IPowerUp iPowerUp : defModule.getPowerups()) {
                powerUps.add(iPowerUp);
            }
            updateModuleRange();
        }

    }
    private void addModules(int i) {
        for (int x = 0; x < i; x++) {
            float endOfLastModulePos = modules.get(modules.size() - 1).getPos().getY();
            ICourseModule tempModule = new DefaultCourseModule(new Vector3f(
                    0,
                    endOfLastModulePos+modules.get(modules.size()-1).getLength(),
                    0),
                    5,
                    1

            );
            modules.add(tempModule);
            tempModule.add3dObjectsToCollisionManager();

            //Add powerups from course module to world so they'll be updated.
            for (IPowerUp iPowerUp : tempModule.getPowerups()) {
                powerUps.add(iPowerUp);
            }
        }
    }
    private void removeModules(int i) {
        for (int x = 0; x < i; x++) {
            ICourseModule module = modules.get(0);
            modules.remove(module);
            module.remove3dObjectsFromCollisionManager();
            module.setActiveState(false);
        }
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        for (com.tda367.parallax.model.core.spacecraft.ISpaceCraft spaceCraft : spaceCrafts) {
            spaceCraft.update(milliSinceLastUpdate);
        }

        List<Integer> numbers = new ArrayList<Integer>();

        for (int i = 0; i < powerUps.size(); i++) {
            powerUps.get(i).update(milliSinceLastUpdate);
            if (powerUps.get(i).isDead()){
                numbers.add(i);
            }
        }

        for (Integer number : numbers) {
            int i = number;
            powerUps.remove(i);
        }
        updateModuleRange();
    }
    @Override
    public void respondToCollision(com.tda367.parallax.model.core.collision.CollisionPair collisionPair) {
        com.tda367.parallax.model.core.collision.Collidable first = collisionPair.getFirstCollidable();
        com.tda367.parallax.model.core.collision.Collidable second = collisionPair.getSecondCollidable();

        first.handleCollision(second);
        second.handleCollision(first);
    }
}
