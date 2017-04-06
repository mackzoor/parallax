package com.tda367.parallax.parallaxCore;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that binds together different course modules and creates several enemy ai's..
 */
public class Course implements Updatable {

    List<CourseModule> modules;
    List<ISpaceCraft> spaceCrafts;

    public Course(){
        spaceCrafts = new ArrayList<ISpaceCraft>();

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

    //TODO Check collisions between spacecraft and obstacles

}
