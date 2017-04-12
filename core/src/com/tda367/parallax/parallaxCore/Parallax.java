package com.tda367.parallax.parallaxCore;

import com.tda367.parallax.parallaxCore.course.Course;
import com.tda367.parallax.parallaxCore.spaceCraft.ISpaceCraft;
import com.tda367.parallax.platform.Sound;

import java.util.List;

/**
 * Created by Anthony on 05/04/2017.
 */
public class Parallax implements Updatable{

    private RenderManager renderManager;
    private SoundManager soundManager;
    private Course course;
    private Camera camera;
    private Player player;

    public Parallax(Player player){
        renderManager = RenderManager.getInstance();
        soundManager = SoundManager.getInstance();

        course = new com.tda367.parallax.parallaxCore.course.Course();
        course.addSpaceCraft(player.getSpaceCraft());

        camera = new Camera();
        camera.trackTo(player.getSpaceCraft());
        this.player = player;

        soundManager.playMusic("Track.mp3");
    }

    @Override
    public void update(int milliSinceLastUpdate) {
        course.update(milliSinceLastUpdate);
        camera.update(milliSinceLastUpdate);
    }

    public Player getPlayer() {
        return player;
    }

    public List<ISpaceCraft> getSpaceCraft(){
        return course.getSpaceCrafts();
    }

    public RenderManager getRenderManager(){
        return renderManager;
    }

    public Camera getCamera(){
        return camera;
    }
}
