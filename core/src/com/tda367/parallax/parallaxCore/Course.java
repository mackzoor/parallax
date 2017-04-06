package com.tda367.parallax.parallaxCore;

import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that binds together different course modules and creates several enemy ai's..
 */
public class Course implements Updatable {

    List<CourseModule> modules;
    List<ISpaceCraft> spaceCrafts;

    public Course(){
        spaceCrafts = new ArrayList<ISpaceCraft>();


        createTestEnemy();
    }

    public void addSpaceCraft(ISpaceCraft spaceCraft){
        spaceCrafts.add(spaceCraft);
    }
    public void removeSpaceCraft(ISpaceCraft spaceCraft){
        spaceCrafts.remove(spaceCraft);
    }

    @Override
    public void update(int milliSinceLastUpdate) {

//        for (CourseModule module : modules){
//
//        }

        for (ISpaceCraft spaceCraft : spaceCrafts){
            spaceCraft.update(milliSinceLastUpdate);
        }

    }

    public List<ISpaceCraft> getSpaceCrafts() {
        return spaceCrafts;
    }

    private void createTestEnemy(){
        Random rand = new Random();

        MinionEnemy minionEnemy = new MinionEnemy(new Agelion(
                new Vector3f(1.5f,-2, 1),
                new Matrix3f(),
                3
        ));
        minionEnemy.getSpaceCraft().setAccelerateTarget(-0.5f);
//        minionEnemy.setTarget(spaceCrafts.get(0));
        spaceCrafts.add(minionEnemy.getSpaceCraft());
    }

    //TODO Check collisions between spacecraft and obstacles

}
