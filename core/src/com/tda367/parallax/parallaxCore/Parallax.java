package com.tda367.parallax.parallaxCore;

/**
 * Created by Anthony on 05/04/2017.
 */
public class Parallax implements Updatable{

    private Course course;

    Parallax(Player player){
        course = new Course();
        course.addSpaceCraft(player.getSpaceCraft());



    }

    @Override
    public void update(int milliSinceLastUpdate) {
        course.update(milliSinceLastUpdate);
        //TODO Camera update pos
    }
}
