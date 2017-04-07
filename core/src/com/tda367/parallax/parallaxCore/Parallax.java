package com.tda367.parallax.parallaxCore;

import com.tda367.parallax.parallaxCore.SpaceCraft.ISpaceCraft;

import java.util.List;

/**
 * Created by Anthony on 05/04/2017.
 */
public class Parallax implements Updatable{

    private Course course;
    private Camera camera;

    public Parallax(Player player){
        course = new Course();
        course.addSpaceCraft(player.getSpaceCraft());

        camera = new Camera();
        camera.trackTo(player.getSpaceCraft());


    }

    @Override
    public void update(int milliSinceLastUpdate) {
        course.update(milliSinceLastUpdate);
        camera.update(milliSinceLastUpdate);
    }

    public List<ISpaceCraft> getSpaceCraft(){
        return course.getSpaceCrafts();
    }

    public Camera getCamera(){
        return camera;
    }
}
