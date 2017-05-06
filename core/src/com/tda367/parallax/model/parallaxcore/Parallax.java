package com.tda367.parallax.model.parallaxcore;

import com.tda367.parallax.model.coreabstraction.RenderManager;
import com.tda367.parallax.model.coreabstraction.SoundManager;
import com.tda367.parallax.model.coreabstraction.Updatable;
import com.tda367.parallax.model.parallaxcore.collision.ICollisionCalculator;
import com.tda367.parallax.model.parallaxcore.course.Course;
import com.tda367.parallax.model.parallaxcore.enemies.HunterAI;
import com.tda367.parallax.model.parallaxcore.enemies.MinionEnemy;
import com.tda367.parallax.model.parallaxcore.spacecraft.Agelion;
import com.tda367.parallax.model.parallaxcore.spacecraft.ISpaceCraft;
import lombok.Getter;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The startup class for the game "Parallax".
 */

public class Parallax implements Updatable {

    @Getter private RenderManager renderManager;
    private SoundManager soundManager;
    private Course course;
    @Getter private Camera camera;
    @Getter private Player player;

    private List<HunterAI> ais;

    public Parallax(Player player){
        renderManager = RenderManager.getInstance();
        soundManager = SoundManager.getInstance();

        course = new Course();
        course.addSpaceCraft(player.getSpaceCraft());

        camera = new Camera();
        camera.trackTo(player.getSpaceCraft());
        this.player = player;

        ais = new ArrayList<HunterAI>();

//        createTestEnemy();

        startBackgroundMusic();
    }

    @Override
    public void update(int milliSinceLastUpdate) {

        if (milliSinceLastUpdate > 100){
            milliSinceLastUpdate = 100;
        }

        for (HunterAI ai : ais){
            ai.update(milliSinceLastUpdate);
        }

        course.update(milliSinceLastUpdate);
        camera.update(milliSinceLastUpdate);

        updateRenderManagerCameraPosition();
    }

    private void updateRenderManagerCameraPosition() {
        RenderManager rm = RenderManager.getInstance();

        rm.setCamXCoord(camera.getPos().getX());
        rm.setCamYCoord(camera.getPos().getY());
        rm.setCamZCoord(camera.getPos().getZ());
    }

    public void setCollisionCalculator(ICollisionCalculator collisionCalculator){
        course.setCollisionCalculator(collisionCalculator);
    }


    public List<ISpaceCraft> getSpaceCraft(){
        return course.getSpaceCrafts();
    }

    private void startBackgroundMusic(){
        Random rand = new Random();
        int randomSong = rand.nextInt(100 - 1 + 1) + 1;

        if(randomSong == 50){
            soundManager.playMusic("secretTrack.mp3","sounds/music");
        } else {
            soundManager.playMusic("track.mp3","sounds/music", 0.7f);
        }
    }

    //Debug only
    private void createTestEnemy() {
        MinionEnemy minionEnemy = new MinionEnemy(new Agelion(
                new Vector3f(1.5f, -2, 1),
                new Quat4f(),
                13
        ));
        minionEnemy.getSpaceCraft().setForwardAcceleration(-3f);
        course.addSpaceCraft(minionEnemy.getSpaceCraft());
        ais.add(minionEnemy);
        minionEnemy.setTarget(player.getSpaceCraft());
    }
}
