package com.tda367.parallax.parallaxCore;

import com.tda367.parallax.parallaxCore.course.Course;
import com.tda367.parallax.parallaxCore.spaceCraft.ISpaceCraft;
import com.tda367.parallax.platform.Sound;

import java.util.List;
import java.util.Random;

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

        Random rand = new Random();
        int randomSong = rand.nextInt(100 - 1 + 1) + 1;

        if(randomSong == 50){
            soundManager.playMusic("secretTrack.mp3");
        } else {
            soundManager.playMusic("Track.mp3", new Float(0.7f));
        }
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
