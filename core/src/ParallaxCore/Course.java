package ParallaxCore;

import java.util.List;

/**
 * A class that binds together different course modules and creates several enemy ai's..
 */
public class Course implements Updatable {

    List<CourseModule> modules;
    List<SpaceCraft> spaceCrafts;

    @Override
    public void update(int milliSinceLastUpdate) {

    }

    //TODO Check collisions between spacecraft and obstacles

}
