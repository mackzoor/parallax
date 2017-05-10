package com.tda367.parallax.model.parallaxcore;

import com.tda367.parallax.model.coreabstraction.RenderQueue;
import com.tda367.parallax.model.coreabstraction.AudioQueue;
import com.tda367.parallax.model.util.Updatable;
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

    @Getter private RenderQueue renderQueue;
    private AudioQueue audioQueue;
    private Course course;
    @Getter private Camera camera;
    @Getter private Player player;

    private List<HunterAI> ais;

    public Parallax(Player player){
        renderQueue = RenderQueue.getInstance();
        audioQueue = AudioQueue.getInstance();

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

        int updateTime = milliSinceLastUpdate;

        if (milliSinceLastUpdate > 100){
            updateTime = 100;
        }

        for (HunterAI ai : ais){
            ai.update(updateTime);
        }

        course.update(updateTime);
        camera.update(updateTime);

        updateRenderManagerCameraPosition();
    }

    private void updateRenderManagerCameraPosition() {
        RenderQueue rm = RenderQueue.getInstance();

        rm.setCamXCoord(camera.getPos().getX());
        rm.setCamYCoord(camera.getPos().getY());
        rm.setCamZCoord(camera.getPos().getZ());
    }

    public List<ISpaceCraft> getSpaceCraft(){
        return course.getSpaceCrafts();
    }

    private void startBackgroundMusic(){
        Random rand = new Random();
        int randomSong = rand.nextInt(100 - 1 + 1) + 1;

        if(randomSong == 50){
            audioQueue.playMusic("secretTrack.mp3","sounds/music");
        } else {
            audioQueue.playMusic("track.mp3","sounds/music", 0.7f);
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
