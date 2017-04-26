package com.tda367.parallax.parallaxCore;

import com.tda367.parallax.parallaxCore.Collision.ICollisionCalculator;
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

        startBackgroundMusic();
    }

    @Override
    public void update(int milliSinceLastUpdate) {

        if (milliSinceLastUpdate > 100){
            milliSinceLastUpdate = 100;
        }

        course.update(milliSinceLastUpdate);
        camera.update(milliSinceLastUpdate);
    }
    public void setCollisionCalculator(ICollisionCalculator collisionCalculator){
        course.setCollisionCalculator(collisionCalculator);
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

    private void startBackgroundMusic(){
        Random rand = new Random();
        int randomSong = rand.nextInt(100 - 1 + 1) + 1;

        if(randomSong == 50){
            soundManager.playMusic("secretTrack.mp3","sounds/music");
        } else {
            soundManager.playMusic("track.mp3","sounds/music", new Float(0.7f));
        }
    }
}
