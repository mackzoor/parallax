package com.tda367.parallax.parallaxCore;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that binds together different course modules and creates several enemy ai's..
 */
public class Course implements Updatable {

    List<CourseModule> modules;
    List<SpaceCraft> spaceCrafts;

    public Course(){
        spaceCrafts = new ArrayList<SpaceCraft>();

    }

    public void addSpaceCraft(SpaceCraft spaceCraft){
        spaceCrafts.add(spaceCraft);
    }
    public void removeSpaceCraft(SpaceCraft spaceCraft){
        spaceCrafts.remove(spaceCraft);
    }

    @Override
    public void update(int milliSinceLastUpdate) {

//        for (CourseModule module : modules){
//
//        }

        for (SpaceCraft spaceCraft : spaceCrafts){
            spaceCraft.update(milliSinceLastUpdate);
        }

    }

    //TODO Check collisions between spacecraft and obstacles

}
