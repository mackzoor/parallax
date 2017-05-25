package com.tda367.parallax.model.core.world;

import com.tda367.parallax.model.core.collision.Collidable;
import com.tda367.parallax.model.core.collision.CollisionManager;
import com.tda367.parallax.model.core.collision.CollisionObserver;
import com.tda367.parallax.model.core.collision.CollisionResult;
import com.tda367.parallax.model.core.powerups.arsenal.IPowerUp;
import com.tda367.parallax.model.core.spacecraft.ISpaceCraft;
import com.tda367.parallax.model.core.util.Updatable;
import com.tda367.parallax.model.core.world.courseobstacles.CourseObstacleBase;
import lombok.Getter;

import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * A course that handles the visual representation and updating of {@link ISpaceCraft} and {@link Collidable}.
 */

public class World implements Updatable, CollisionObserver {
    @Getter
    private List<ICourseModule> modules;
    @Getter
    private List<ISpaceCraft> spaceCrafts;
    //TODO, remove the power-up after used
    @Getter
    private List<IPowerUp> powerUps;

    public World() {
        CollisionManager.getInstance().subscribeToCollisions(this);
        this.modules = new ArrayList<ICourseModule>();
        this.spaceCrafts = new ArrayList<ISpaceCraft>();
        this.powerUps = new ArrayList<IPowerUp>();

        updateModuleRange();
    }

    public void addSpaceCraft(ISpaceCraft spaceCraft) {
        this.spaceCrafts.add(spaceCraft);
        spaceCraft.addToCollisionManager();
    }

    void removeSpaceCraft(ISpaceCraft spaceCraft) {
        this.spaceCrafts.remove(spaceCraft);
        spaceCraft.addToCollisionManager();
    }

    private float getFirstSpaceCraftYPosition() {
        if (this.spaceCrafts.size() > 0) {
            float yPosition = this.spaceCrafts.get(0).getPos().getY();
            for (int i = 1; i < this.spaceCrafts.size(); i++) {
                float tempYPosition = this.spaceCrafts.get(i).getPos().getY();
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
        if (this.spaceCrafts.size() > 0) {
            float yPosition = this.spaceCrafts.get(0).getPos().getY();
            for (int i = 1; i < this.spaceCrafts.size(); i++) {
                float tempYPosition = this.spaceCrafts.get(i).getPos().getY();
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

        if (this.modules.size() > 0) {
            float firstCraft = this.getFirstSpaceCraftYPosition();
            float lastCraft = this.getLastSpaceCraftYPosition();

            float firstModule = this.modules.get(this.modules.size() - 1).getPos().getY() + this.modules.get(this.modules.size() - 1).getLength();
            float lastModule = this.modules.get(0).getPos().getY();

            int modulesToAdd = (int) ((firstCraft + 256 - firstModule) / 64);
            int modulesToRemove = (int) ((lastCraft - lastModule) / 128);


            addModules(modulesToAdd);
            removeModules(modulesToRemove);
        } else {
            ICourseModule defModule = new DefaultCourseModule(new Vector3f(0, 32, 0), 0, 0);
            this.modules.add(defModule);

            this.powerUps.addAll(defModule.getPowerups());

            this.updateModuleRange();
        }

    }

    private void addModules(int i) {
        for (int x = 0; x < i; x++) {
            float endOfLastModulePos = this.modules.get(this.modules.size() - 1).getPos().getY();
            ICourseModule tempModule = new DefaultCourseModule(new Vector3f(
                    0,
                    endOfLastModulePos + this.modules.get(this.modules.size() - 1).getLength(),
                    0),
                    5,
                    1

            );
            this.modules.add(tempModule);
            tempModule.add3dObjectsToCollisionManager();

            //Add powerups from course module to world so they'll be updated.
            this.powerUps.addAll(tempModule.getPowerups());
        }
    }

    private void removeModules(int i) {
        for (int x = 0; x < i; x++) {
            ICourseModule module = this.modules.get(0);
            this.modules.remove(module);
            module.remove3dObjectsFromCollisionManager();
            module.setActiveState(false);
        }
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        //Update spacecraft
        for (ISpaceCraft spaceCraft : this.spaceCrafts) {
            spaceCraft.update(milliSinceLastUpdate);
        }

        //Update obstacles in each module.
        for (ICourseModule module : this.modules) {
            for (CourseObstacleBase courseObstacleBase : module.getCouseObstacles()) {
                courseObstacleBase.update(milliSinceLastUpdate);
            }
        }


        List<Integer> numbers = new ArrayList<Integer>();
        //Update powerups and find dead ones.
        for (int i = 0; i < this.powerUps.size(); i++) {
            this.powerUps.get(i).update(milliSinceLastUpdate);
            if (this.powerUps.get(i).isDead()) {
                numbers.add(i);
            }
        }

        //remove dead powerups.
        for (Integer number : numbers) {
            int i = number;
            this.powerUps.remove(i);
        }

        //Update module range
        this.updateModuleRange();
    }

    @Override
    public void respondToCollision(CollisionResult collisionResult) {
        Collidable first = collisionResult.getFirst();
        Collidable second = collisionResult.getSecond();

        first.handleCollision(second);
        second.handleCollision(first);
    }
}
